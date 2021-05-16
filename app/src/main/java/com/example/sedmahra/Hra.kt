package com.example.sedmahra
import android.os.*
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.sedmahra.databinding.FragmentHraBinding
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*
import kotlin.collections.ArrayList


class Hra (var binding : FragmentHraBinding?, var nacitanaHra: Boolean, val hrac: Hrac = Hrac(), val protihrac: Hrac = Hrac(), var karty: ArrayList<Karta> = ArrayList<Karta>(),
           var ziskaneKartyHrac: ArrayList<Karta> = ArrayList<Karta>(), var hracVyhralKolo: Boolean = true, var kartyNaStole: ArrayList<Karta> = ArrayList<Karta>()
            ) :Serializable{
    init {
        if(nacitanaHra == false) {
            this.inicializujKarty()
            this.rozdajKarty()
        }
        this.aktualizujZobrazenieKariet()
        this.aktualizujZobrazenieTextu()
        this.nastavListenery()

    }

    public fun nastavListenery() {
        binding?.kartaRuka1?.setOnClickListener() {
            hracovTah(0);
            this.aktualizujZobrazenieKariet()
        }
        binding?.kartaRuka2?.setOnClickListener() {
            hracovTah(1)
            this.aktualizujZobrazenieKariet()
        }
        binding?.kartaRuka3?.setOnClickListener() {
            hracovTah(2)
            this.aktualizujZobrazenieKariet()
        }
        binding?.kartaRuka4?.setOnClickListener() {
            hracovTah(3)
            this.aktualizujZobrazenieKariet()
        }
        binding?.berButton?.setOnClickListener() {
            if(this.kartyNaStole.size ==2 ||this.kartyNaStole.size ==4 || this.kartyNaStole.size ==6 || this.kartyNaStole.size ==8 )
            this.koloSkoncilo()
            this.aktualizujZobrazenieKariet()
        }
    }


    private fun hracovTah(indexKarty : Int) {
        if(this.kartyNaStole.size == 1 || this.kartyNaStole.size == 3 || this.kartyNaStole.size == 5 || this.kartyNaStole.size == 7) {
            val karta: Karta = this.hrac.kartyRuka.removeAt(indexKarty);
            this.kartyNaStole.add(karta);
            if(this.kartyNaStole.get(this.kartyNaStole.size-1).typ == this.kartyNaStole.get(0).typ ||
                this.kartyNaStole.get(this.kartyNaStole.size-1).typ == TypKarty.SEDEM) {
                this.hracVyhralKolo = true;
                if(this.kartyNaStole.size == 8) {
                    this.koloSkoncilo()
                }
                else {
                    this.protihracovTah()
                }
            }
            else {
                this.koloSkoncilo()
            }
        }
        else {
            if(kartyNaStole.size > 0) {
                if(hrac.kartyRuka[indexKarty].typ != this.kartyNaStole[0].typ &&
                    hrac.kartyRuka[indexKarty].typ != TypKarty.SEDEM) {
                    Toast.makeText(binding?.root?.context, "Táto karta  nemôže zabiť", Toast.LENGTH_SHORT).show()
                }
                else {
                    val karta : Karta = this.hrac.kartyRuka.removeAt(indexKarty);
                    this.kartyNaStole.add(karta)
                    this.hracVyhralKolo = true
                    this.protihracovTah()
                }
            } else {
                val karta : Karta = this.hrac.kartyRuka.removeAt(indexKarty);
                this.kartyNaStole.add(karta)
                this.protihracovTah()
            }
        }
    }

    private fun koloSkoncilo() {
        this.aktualizujZobrazenieKariet()
        //spocitanie bodov
        Handler(Looper.getMainLooper()).postDelayed({
            var bodyVKole: Int = 0;
            for(karta in this.kartyNaStole) {
                if(karta.typ == TypKarty.ESO || karta.typ == TypKarty.DESAT) {
                    bodyVKole ++;
                }
            }
            if(this.hracVyhralKolo) {

                this.hrac.body += bodyVKole
                this.aktualizujZobrazenieTextu()
                for(karta in this.kartyNaStole) {
                    this.ziskaneKartyHrac.add(karta)
                }
                kartyNaStole.clear()
                this.rozdajKarty()
            }
            else {
                this.protihrac.body += bodyVKole
                this.aktualizujZobrazenieTextu()
                this.kartyNaStole.clear()
                this.rozdajKarty()
                if(protihrac.kartyRuka.size!= 0) {
                    val karta : Karta = protihrac.kartyRuka.removeLast()
                    this.kartyNaStole.add(karta)
                }
            }
            if(this.hrac.kartyRuka.size == 0) {
                val bundle = Bundle()
                bundle.putParcelableArrayList("ziskane karty", this.ziskaneKartyHrac)
                bundle.putInt("ziskane body", this.hrac.body)

                val kalendar = Calendar.getInstance()
                val formatovacDatumuACasu = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
                val datumACas = formatovacDatumuACasu.format(kalendar.time)

                val vysledokHry = VysledokHry(this.hrac.body, datumACas)
                val zapisovac : UkladanieVysledkovHry = UkladanieVysledkovHry()
                zapisovac.zapisNovy(vysledokHry, this.binding?.root?.context)

                binding?.root?.findNavController()?.navigate(R.id.action_hraFragment_to_vysledokHryFragment, bundle)
            }

            this.aktualizujZobrazenieKariet()
        }, 1000)

    }


    private fun protihracovTah() {
        if(this.kartyNaStole.size == 1 || this.kartyNaStole.size == 3 ||  this.kartyNaStole.size == 5 || this.kartyNaStole.size == 7) {
            val index = this.dajIndexKartyZRukyProtihraca(this.kartyNaStole[0].typ)
            if(index != -1) {
                val karta : Karta = this.protihrac.kartyRuka.removeAt(index)
                this.kartyNaStole.add(karta)
                this.hracVyhralKolo = false
            } else {
                val indexSedem = this.dajIndexKartyZRukyProtihraca(TypKarty.SEDEM)
                if(index != -1) {
                    if(this.jeNaStoleDesatAleboEso()) {
                        val karta = this.protihrac.kartyRuka.removeAt(indexSedem)
                        this.kartyNaStole.add(karta)
                        this.hracVyhralKolo = false
                    } else {
                        val indexObycajnaKarta = this.maProtihracObycajnuKartu()
                        if(indexObycajnaKarta != -1) {
                            val karta = this.protihrac.kartyRuka.removeAt(indexObycajnaKarta)
                            this.kartyNaStole.add(karta)
                            this.koloSkoncilo()
                        }
                        else {
                            val karta = this.protihrac.kartyRuka.removeAt(indexSedem)
                            this.kartyNaStole.add(karta)
                            this.hracVyhralKolo = false
                        }

                    }
                } else {
                    val indexObycajnaKarta = this.maProtihracObycajnuKartu()
                    if(indexObycajnaKarta != -1) {
                        val karta = this.protihrac.kartyRuka.removeAt(indexObycajnaKarta)
                        this.kartyNaStole.add(karta)
                    } else {
                        val karta = this.protihrac.kartyRuka.removeAt(0)
                        this.kartyNaStole.add(karta)

                    }
                    koloSkoncilo()
                }
            }

            if(this.kartyNaStole.size == 8) {
                this.koloSkoncilo()
            } else {
                return
            }

        } else {
            val index = this.dajIndexKartyZRukyProtihraca(this.kartyNaStole[0].typ)
            if(index != -1) {
                val karta = this.protihrac.kartyRuka.removeAt(index)
                this.kartyNaStole.add(karta)
                this.hracVyhralKolo= false
            }
            else {
                val indexSedem = this.dajIndexKartyZRukyProtihraca(TypKarty.SEDEM)
                if(indexSedem != -1) {
                    if(this.jeNaStoleDesatAleboEso()) {
                        val karta = this.protihrac.kartyRuka.removeAt(indexSedem)
                        this.kartyNaStole.add(karta)
                        this.hracVyhralKolo = false
                    }   else {
                        this.koloSkoncilo()
                    }
                }  else {
                    this.koloSkoncilo()
                }
            }
        }

    }


    private fun maProtihracObycajnuKartu() : Int {
        var indexObycajnaKarta: Int = -1;
        for(i in 0..(this.protihrac.kartyRuka.size-1)) {
            if(protihrac.kartyRuka[i].typ != TypKarty.DESAT && protihrac.kartyRuka[i].typ != TypKarty.ESO
                && protihrac.kartyRuka[i].typ != TypKarty.SEDEM) {
                indexObycajnaKarta = i
            }
        }
        return indexObycajnaKarta
    }
    private fun ber() {
        if(this.hracVyhralKolo == false) {
            if(this.kartyNaStole.size == 2 || this.kartyNaStole.size == 4 || this.kartyNaStole.size == 6) {
                this.koloSkoncilo()
            }
        }
    }



    private fun dajIndexKartyZRukyProtihraca(typKarty: TypKarty) : Int {
        var index: Int = -1
        for(i in 0..(this.protihrac.kartyRuka.size-1)) {
            if(protihrac.kartyRuka[i].typ == typKarty) {
                index = i;
            }
        }
        return index
    }

    private fun jeNaStoleDesatAleboEso() : Boolean {
        for(karta in this.kartyNaStole) {
            if(karta.typ == TypKarty.DESAT || karta.typ == TypKarty.ESO) {
                return true
            }
        }
        return false
    }

    fun aktualizujZobrazenieTextu() {
        binding?.vaseBodyTextView?.text = binding?.root?.resources?.getString(R.string.hracove_body, this.hrac.body)
        binding?.protihracoveBodyTextView?.text = binding?.root?.resources?.getString(R.string.protihracove_body, this.protihrac.body)
        binding?.zostavaKarietTextView?.text = binding?.root?.resources?.getString(R.string.zostava_kariet, this.karty.size)
    }

    fun aktualizujZobrazenieKariet() {
        this.zobrazKartyRuka()
        this.zobrazVylozeneKarty()
    }

    private fun zobrazKartyRuka() {
        //android.R.color.transparent - ziadny obrazok

        //4. karta
        if(this.hrac.kartyRuka.size >3) {
            binding?.kartaRuka4?.setImageResource(hrac.kartyRuka[3].obrazok)
        } else {
            binding?.kartaRuka4?.setImageResource(android.R.color.transparent)
        }
        //3. karta
        if(this.hrac.kartyRuka.size >2) {
            binding?.kartaRuka3?.setImageResource(hrac.kartyRuka[2].obrazok)
        } else {
            binding?.kartaRuka3?.setImageResource(android.R.color.transparent)
        }
        //2. karta
        if(this.hrac.kartyRuka.size >1) {
            binding?.kartaRuka2?.setImageResource(hrac.kartyRuka[1].obrazok)
        } else {
            binding?.kartaRuka2?.setImageResource(android.R.color.transparent)
        }
        //1.karta
        if(this.hrac.kartyRuka.size >0) {
            binding?.kartaRuka1?.setImageResource(hrac.kartyRuka[0].obrazok)
        } else {
            binding?.kartaRuka1?.setImageResource(android.R.color.transparent)
        }
    }

    private fun zobrazVylozeneKarty() {

        val imageViewKariet = arrayOf(binding?.karta1, binding?.karta2, binding?.karta3, binding?.karta4, binding?.karta5, binding?.karta6, binding?.karta7, binding?.karta8)
        for(i in (imageViewKariet.size-1) downTo 0) {
            if(this.kartyNaStole.size > i) {
                imageViewKariet[i]?.setImageResource(this.kartyNaStole[i].obrazok)
            } else {
                imageViewKariet[i]?.setImageResource(android.R.color.transparent)
            }
        }
    }
private fun rozdajKarty() {
        while(hrac.kartyRuka.size != 4 && this.karty.size != 0) {
            var karta: Karta = this.karty.removeLast()
            this.hrac.kartyRuka.add(karta)
            karta = this.karty.removeLast()
            this.protihrac.kartyRuka.add(karta)
        }
        this.aktualizujZobrazenieTextu()
    }

    private fun inicializujKarty() {
        //sedmicky:
        this.karty.add(Karta(TypKarty.SEDEM, R.drawable.karta_7c))
        this.karty.add(Karta(TypKarty.SEDEM, R.drawable.karta_7g))
        this.karty.add(Karta(TypKarty.SEDEM, R.drawable.karta_7l))
        this.karty.add(Karta(TypKarty.SEDEM, R.drawable.karta_7z))
        //osmicky
        this.karty.add(Karta(TypKarty.OSEM, R.drawable.karta_8c))
        this.karty.add(Karta(TypKarty.OSEM, R.drawable.karta_8g))
        this.karty.add(Karta(TypKarty.OSEM, R.drawable.karta_8l))
        this.karty.add(Karta(TypKarty.OSEM, R.drawable.karta_8z))
        //deviny
        this.karty.add(Karta(TypKarty.DEVAT, R.drawable.karta_9c))
        this.karty.add(Karta(TypKarty.DEVAT, R.drawable.karta_9g))
        this.karty.add(Karta(TypKarty.DEVAT, R.drawable.karta_9l))
        this.karty.add(Karta(TypKarty.DEVAT, R.drawable.karta_9z))
        //desiny
        this.karty.add(Karta(TypKarty.DESAT, R.drawable.karta_10c))
        this.karty.add(Karta(TypKarty.DESAT, R.drawable.karta_10g))
        this.karty.add(Karta(TypKarty.DESAT, R.drawable.karta_10l))
        this.karty.add(Karta(TypKarty.DESAT, R.drawable.karta_10z))
        //dolek
        this.karty.add(Karta(TypKarty.DOLEK, R.drawable.karta_dc))
        this.karty.add(Karta(TypKarty.DOLEK, R.drawable.karta_dg))
        this.karty.add(Karta(TypKarty.DOLEK, R.drawable.karta_dl))
        this.karty.add(Karta(TypKarty.DOLEK, R.drawable.karta_dz))
        //horek
        this.karty.add(Karta(TypKarty.HOREK, R.drawable.karta_hc))
        this.karty.add(Karta(TypKarty.HOREK, R.drawable.karta_hg))
        this.karty.add(Karta(TypKarty.HOREK, R.drawable.karta_hl))
        this.karty.add(Karta(TypKarty.HOREK, R.drawable.karta_hz))
        //kral
        this.karty.add(Karta(TypKarty.KRAL, R.drawable.karta_kc))
        this.karty.add(Karta(TypKarty.KRAL, R.drawable.karta_kg))
        this.karty.add(Karta(TypKarty.KRAL, R.drawable.karta_kl))
        this.karty.add(Karta(TypKarty.KRAL, R.drawable.karta_kz))
        //eso
        this.karty.add(Karta(TypKarty.ESO, R.drawable.karta_ec))
        this.karty.add(Karta(TypKarty.ESO, R.drawable.karta_eg))
        this.karty.add(Karta(TypKarty.ESO, R.drawable.karta_el))
        this.karty.add(Karta(TypKarty.ESO, R.drawable.karta_ez))
        //zamiesanie
        this.karty.shuffle()
    }




}