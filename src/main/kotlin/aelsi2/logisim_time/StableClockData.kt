package aelsi2.logisim_time

import com.cburch.logisim.instance.InstanceData

data class StableClockData(
    var startTime: Long,
    var enabled: Boolean,
    var clock: Boolean,
    var delayMillis: Int
) : InstanceData {
    override fun clone(): Any = copy()
}