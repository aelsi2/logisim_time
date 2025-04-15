package aelsi2.logisim_time

import com.cburch.logisim.util.LocaleManager
import com.cburch.logisim.util.StringGetter

object TimeStrings {
    const val LIBRARY_NAME = "Time"
    const val COMPONENT_STABLE_CLOCK = "Stable Clock"
    const val PORT_LABEL_ENABLE = "en"
    const val PORT_TOOLTIP_ENABLE = "Enable: When 0, output is set to 0"
    const val PORT_TOOLTIP_CLOCK = "Clock: Updates on rising edge"
    const val PORT_TOOLTIP_OUTPUT = "Current clock output"
    const val ATTRIBUTE_DELAY = "Delay (ms)"

    private val source: LocaleManager = LocaleManager("resources/logisim", "std")

    fun getter(key: String): StringGetter = source.getter(key)
    fun get(key: String): String = source.get(key)
}