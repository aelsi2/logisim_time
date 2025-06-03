package aelsi2.logisim_time

import com.cburch.logisim.util.StringGetter
import java.util.*

@Suppress("UNCHECKED_CAST")
object TimeStrings {
    const val LIBRARY_NAME = "library_name"
    const val COMPONENT_STABLE_CLOCK = "component_stable_clock"
    const val PORT_LABEL_ENABLE = "port_label_enable"
    const val PORT_TOOLTIP_ENABLE = "port_tooltip_enable"
    const val PORT_TOOLTIP_CLOCK = "port_tooltip_clock"
    const val PORT_TOOLTIP_OUTPUT = "port_tooltip_output"
    const val ATTRIBUTE_DELAY = "attribute_delay"

    const val BUNDLE_NAME = "logisim_time"

    // getBundle caches resource bundles by default so this is fine
    private val bundle
        get() = ResourceBundle.getBundle(BUNDLE_NAME)

    fun getter(key: String): StringGetter = StringGetter { bundle.getString(key) }
    fun get(key: String): String = bundle.getString(key)
}