package aelsi2.logisim_time

import com.cburch.logisim.data.AbstractAttributeSet
import com.cburch.logisim.data.Attribute
import com.cburch.logisim.data.Attributes

class StableClockAttributes : AbstractAttributeSet() {
    private var delayMillis: Int = 1000

    override fun getAttributes(): MutableList<Attribute<*>> = ATTRIBUTE_LIST

    @Suppress("UNCHECKED_CAST")
    override fun <V : Any?> getValue(attribute: Attribute<V>?): V = when (attribute) {
        DELAY_ATTRIBUTE -> delayMillis as V
        else -> null as V
    }

    override fun <V : Any?> setValue(attribute: Attribute<V>?, value: V) {
        if (attribute == DELAY_ATTRIBUTE && value is Number) {
            delayMillis = value.toInt()
            fireAttributeValueChanged(DELAY_ATTRIBUTE, delayMillis)
        }
    }

    override fun copyInto(attribtues: AbstractAttributeSet?) {
        if (attribtues is StableClockAttributes) {
            attribtues.delayMillis = delayMillis
        }
    }

    companion object {
        val DELAY_ATTRIBUTE: Attribute<Int> = Attributes.forIntegerRange(
            "delay",
            TimeStrings.getter(TimeStrings.ATTRIBUTE_DELAY),
            1, 60_000)
        private val ATTRIBUTE_LIST = mutableListOf<Attribute<*>>(DELAY_ATTRIBUTE)
    }
}