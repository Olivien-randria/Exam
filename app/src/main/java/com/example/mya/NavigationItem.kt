package com.example.mya

sealed class NavigationItem(var route: String, var icon:Int, var title: String)
{
    object Introduction: NavigationItem("introduction",R.drawable.ic_introduction, "Fampidirana")
    object Selfgrowth: NavigationItem("selfgrowth",R.drawable.ic_selfgrowth, "Olona sahy")
    object Communication: NavigationItem("communication",R.drawable.ic_communication, "Fahaiza-mifandray")
    object Leadership: NavigationItem("leadership",R.drawable.ic_leadership, "Fahaiza-mitarika")
    object Management: NavigationItem("management",R.drawable.ic_management,"Fahaiza-mitatana")
    object Accounting: NavigationItem("accounting",R.drawable.ic_accounting, "Fahaiza-mitatana vola")
    object Marketing: NavigationItem("marketing",R.drawable.ic_marketing, "Fahaiza-mivarotra")
    object Negociation: NavigationItem("negociation",R.drawable.ic_negociation, "Fahaiza-mifampiraharaha")
    object Enterprenership: NavigationItem("enterprenership",R.drawable.ic_entreprenership,"Fahaiza-mandraharaha")


}
