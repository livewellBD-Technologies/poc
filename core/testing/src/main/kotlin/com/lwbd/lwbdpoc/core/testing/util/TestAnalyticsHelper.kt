//package com.lwbd.lwbdpoc.core.testing.util
//
//import com.lwbd.lwbdpoc.core.analytics.AnalyticsEvent
//import com.lwbd.lwbdpoc.core.analytics.AnalyticsHelper
//
//class TestAnalyticsHelper : AnalyticsHelper {
//
//    private val events = mutableListOf<AnalyticsEvent>()
//    override fun logEvent(event: AnalyticsEvent) {
//        events.add(event)
//    }
//
//    fun hasLogged(event: AnalyticsEvent) = event in events
//}
