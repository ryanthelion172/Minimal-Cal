package com.example.mincal

enum class SortType {
    ALL_EVENTS,
    DAY_EVENTS,
    ALL_NAME
}

// Enum for sorting days in a month
enum class SortDaysInMonth {
    DAY_1, DAY_2, DAY_3, DAY_4, DAY_5, DAY_6, DAY_7, DAY_8, DAY_9, DAY_10,
    DAY_11, DAY_12, DAY_13, DAY_14, DAY_15, DAY_16, DAY_17, DAY_18, DAY_19, DAY_20,
    DAY_21, DAY_22, DAY_23, DAY_24, DAY_25, DAY_26, DAY_27, DAY_28, DAY_29, DAY_30,
    DAY_31
};

enum class SortMonthsInYear {
    MONTH_1, MONTH_2, MONTH_3, MONTH_4, MONTH_5, MONTH_6, MONTH_7, MONTH_8, MONTH_9,
    MONTH_10, MONTH_11, MONTH_12
};

fun SortMonthsInYear.toMonthName(): String {
    return when (this) {
        SortMonthsInYear.MONTH_1 -> "January"
        SortMonthsInYear.MONTH_2 -> "February"
        SortMonthsInYear.MONTH_3 -> "March"
        SortMonthsInYear.MONTH_4 -> "April"
        SortMonthsInYear.MONTH_5 -> "May"
        SortMonthsInYear.MONTH_6 -> "June"
        SortMonthsInYear.MONTH_7 -> "July"
        SortMonthsInYear.MONTH_8 -> "August"
        SortMonthsInYear.MONTH_9 -> "September"
        SortMonthsInYear.MONTH_10 -> "October"
        SortMonthsInYear.MONTH_11 -> "November"
        SortMonthsInYear.MONTH_12 -> "December"
    }
}


// Enum for sorting years between 2000 and 2050
enum class SortYears {
    YEAR_2000, YEAR_2001, YEAR_2002, YEAR_2003, YEAR_2004, YEAR_2005,
    YEAR_2006, YEAR_2007, YEAR_2008, YEAR_2009, YEAR_2010, YEAR_2011,
    YEAR_2012, YEAR_2013, YEAR_2014, YEAR_2015, YEAR_2016, YEAR_2017,
    YEAR_2018, YEAR_2019, YEAR_2020, YEAR_2021, YEAR_2022, YEAR_2023,
    YEAR_2024, YEAR_2025, YEAR_2026, YEAR_2027, YEAR_2028, YEAR_2029,
    YEAR_2030, YEAR_2031, YEAR_2032, YEAR_2033, YEAR_2034, YEAR_2035,
    YEAR_2036, YEAR_2037, YEAR_2038, YEAR_2039, YEAR_2040, YEAR_2041,
    YEAR_2042, YEAR_2043, YEAR_2044, YEAR_2045, YEAR_2046, YEAR_2047,
    YEAR_2048, YEAR_2049, YEAR_2050
};
// Extension function for SortDaysInMonth to convert it to the appropriate day of the month as an Int
fun SortDaysInMonth.toDayOfMonth(): Int {
    return this.name.substringAfter("_").toInt()
}

// Extension function for SortMonthsInYear to convert it to the appropriate month number as an Int
fun SortMonthsInYear.toMonthNumber(): Int {
    return this.ordinal + 1
}

// Extension function for SortYears to convert it to the appropriate year number as an Int
fun SortYears.toYearNumber(): Int {
    return this.name.substringAfter("_").toInt()
}
