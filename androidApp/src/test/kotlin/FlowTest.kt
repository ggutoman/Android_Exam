import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FlowTest {

    @Test
    fun TestFlow() = runTest{

            flow {
                emit(createResult(1))
            }.collect {
                when(it){
                    is DoEvents.onSuccess -> System.out.println(it.res)
                    is DoEvents.onFailed -> System.out.println(it.res)
                }
            }
    }

    fun createResult(param: Int): DoEvents<String>{
        return if (param > 0){
            DoEvents.onSuccess("Greater than 0")
        }else{
            DoEvents.onFailed("Less than equal to 0")
        }
    }

    interface DoEvents<out T>{
        class onSuccess<T>(val res: String): DoEvents<T>
        class onFailed<T>(val res: String): DoEvents<T>
    }
}