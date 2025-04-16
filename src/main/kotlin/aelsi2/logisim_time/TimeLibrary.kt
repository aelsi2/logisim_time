package aelsi2.logisim_time

import com.cburch.logisim.tools.AddTool
import com.cburch.logisim.tools.Library
import com.cburch.logisim.tools.Tool

@Suppress("UNUSED")
class TimeLibrary : Library() {
    private val _tools = mutableListOf(AddTool(StableClockComponent()))

    override fun getTools(): MutableList<out Tool> {
        return _tools
    }

    override fun getDisplayName(): String {
        return TimeStrings.get(TimeStrings.LIBRARY_NAME)
    }
}