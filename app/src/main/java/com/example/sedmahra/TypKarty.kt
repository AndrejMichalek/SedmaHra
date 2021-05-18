package com.example.sedmahra

import java.io.Serializable

/**
 * Typ karty
 *
 * @constructor Create empty Typ karty
 */
enum class TypKarty : Serializable {
    /**
     * Karta typu sedem
     *
     * @constructor Create empty S e d e m
     */
    SEDEM,

    /**
     * Karta typu osem
     *
     * @constructor Create empty O s e m
     */
    OSEM,

    /**
     * Karta typu deväť
     *
     * @constructor Create empty D e v a t
     */
    DEVAT,

    /**
     * Karta typu desať
     *
     * @constructor Create empty D e s a t
     */
    DESAT,

    /**
     * Karta typu horek
     *
     * @constructor Create empty H o r e k
     */
    HOREK,

    /**
     * Karta typu dolek
     *
     * @constructor Create empty D o l e k
     */
    DOLEK,

    /**
     * Karta typu kráľ
     *
     * @constructor Create empty K r a l
     */
    KRAL,

    /**
     * Karta typu eso
     *
     * @constructor Create empty E s o
     */
    ESO
}