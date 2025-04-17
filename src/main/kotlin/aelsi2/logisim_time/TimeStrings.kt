package aelsi2.logisim_time

import com.cburch.logisim.util.LocaleListener
import com.cburch.logisim.util.LocaleManager
import com.cburch.logisim.util.StringGetter
import java.util.Locale
import java.util.ResourceBundle

@Suppress("UNCHECKED_CAST")
object TimeStrings {
    const val LIBRARY_NAME = "library_name"
    const val COMPONENT_STABLE_CLOCK = "component_stable_clock"
    const val PORT_LABEL_ENABLE = "port_label_enable"
    const val PORT_TOOLTIP_ENABLE = "port_tooltip_enable"
    const val PORT_TOOLTIP_CLOCK = "port_tooltip_clock"
    const val PORT_TOOLTIP_OUTPUT = "port_tooltip_output"
    const val ATTRIBUTE_DELAY = "attribute_delay"

    init {
        // Pizdec
        val field = LocaleManager::class.java.getDeclaredField("listeners")
        field.isAccessible = true
        val listeners = field.get(null) as ArrayList<LocaleListener>
        listeners.add(0) {
            bundle = loadBundle(LocaleManager.getLocale())
        }
    }

    private var bundle = loadBundle(LocaleManager.getLocale())

    fun getter(key: String): StringGetter = StringGetter { bundle.getString(key) }
    fun get(key: String): String = bundle.getString(key)

    private fun loadBundle(locale: Locale): ResourceBundle = ResourceBundle.getBundle("logisim_time", locale)
}