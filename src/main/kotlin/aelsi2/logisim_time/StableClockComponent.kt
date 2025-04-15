package aelsi2.logisim_time

import com.cburch.logisim.data.*
import com.cburch.logisim.instance.InstanceFactory
import com.cburch.logisim.instance.InstancePainter
import com.cburch.logisim.instance.InstanceState
import com.cburch.logisim.instance.Port

class StableClockComponent : InstanceFactory(
    "stable_clock",
    TimeStrings.getter(TimeStrings.COMPONENT_STABLE_CLOCK)
) {
    init {
        setOffsetBounds(Bounds.create(0, 0, 20, 20))
        ports = listOf(
            Port(0, 10, Port.INPUT, 1)
                .apply { setToolTip(TimeStrings.getter(TimeStrings.PORT_TOOLTIP_ENABLE)) },
            Port(10, 20, Port.INPUT, 1)
                .apply { setToolTip(TimeStrings.getter(TimeStrings.PORT_TOOLTIP_CLOCK)) },
            Port(20, 10, Port.OUTPUT, 1)
                .apply { setToolTip(TimeStrings.getter(TimeStrings.PORT_TOOLTIP_OUTPUT)) },
        )
    }

    override fun paintInstance(painter: InstancePainter) {
        with(painter) {
            drawBounds()
            drawPort(Ports.ENABLE, TimeStrings.get(TimeStrings.PORT_LABEL_ENABLE), Direction.EAST)
            drawPort(Ports.OUTPUT)
            drawClock(Ports.CLOCK, Direction.NORTH)
        }
    }

    override fun propagate(state: InstanceState) {
        val data = state.getClockData()
        val clock = state.getPort(Ports.CLOCK).toIntValue() == 1
        val enabled = state.getPort(Ports.ENABLE).toIntValue() != 0

        if (state.getPort(Ports.OUTPUT).toIntValue() < 0) {
            state.setPort(Ports.OUTPUT, Value.createKnown(BitWidth.ONE, 0), PORT_DELAY)
        }
        if (!enabled) {
            state.setPort(Ports.OUTPUT, Value.createKnown(BitWidth.ONE, 0), PORT_DELAY)
            data.enabled = false
            return
        }
        if (data.clock || !clock) {
            data.clock = clock
            return
        }

        val time = System.currentTimeMillis()
        if (!data.enabled) {
            data.startTime = time
        }
        val outValue = ((time - data.startTime) / data.delayMillis).toInt() and 1
        state.setPort(Ports.OUTPUT, Value.createKnown(BitWidth.ONE, outValue), PORT_DELAY)

        data.enabled = true
        data.clock = true
    }

    override fun createAttributeSet(): AttributeSet {
        return StableClockAttributes()
    }

    object Ports {
        const val ENABLE = 0
        const val CLOCK = 1
        const val OUTPUT = 2
    }

    companion object {
        private const val PORT_DELAY = 1

        private fun InstanceState.getClockData(): StableClockData =
            (data as StableClockData?) ?: run {
                val clockData = StableClockData(
                    startTime = System.currentTimeMillis(),
                    enabled = true,
                    clock = false,
                    delayMillis = instance.getAttributeValue(StableClockAttributes.DELAY_ATTRIBUTE)
                )
                instance.attributeSet.addAttributeListener(object : AttributeListener {
                    override fun attributeListChanged(event: AttributeEvent) {}
                    override fun attributeValueChanged(event: AttributeEvent) {
                        if (event.attribute == StableClockAttributes.DELAY_ATTRIBUTE) {
                            clockData.delayMillis = event.value as Int
                            clockData.startTime = System.currentTimeMillis()
                        }
                    }
                })
                data = clockData
                clockData
            }
    }
}