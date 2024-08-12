import com.pg.android_exam.android.Model.CoroutineTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CoroutineTest {

    @Test
    fun TestBackground() = runTest {

        CoroutineTask.ExecuteTask("HI, I AM PARAMETER",
            onLoad = {
                System.out.println("BACKGROUND PREPARING")
            },
            onBackground = {
                System.out.println(it.toString())
                it.let { "Parameter finished" }
            },
            onFinished = {
                System.out.println(it)
            }

        )

    }
}