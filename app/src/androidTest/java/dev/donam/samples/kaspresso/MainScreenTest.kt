package dev.donam.samples.kaspresso

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.withAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.params.ScreenshotParams
import com.kaspersky.kaspresso.params.VideoParams
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.kaspersky.kaspresso.testcases.api.testcaserule.TestCaseRule
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


object MainScreen : KScreen<MainScreen>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    val centerText = KTextView { withId(R.id.centerText) }
    val centerButton = KTextView { withId(R.id.centerButton) }
}

@RunWith(AndroidJUnit4::class)
class MainScreenTest : TestCase() {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val testCaseRule = TestCaseRule(
        testClassName = javaClass.simpleName,
        kaspressoBuilder = Kaspresso.Builder.withAllureSupport {
            videoParams = VideoParams(bitRate = 10_000_000)
            screenshotParams = ScreenshotParams(quality = 1)
        }
    )

    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainScreen_centerText_should_be_correct() = run {
        MainScreen {
            centerText {
                isVisible()
                hasText("Hello World!")
            }
        }
    }

    @Test
    fun mainScreen_centerButton_should_update_centerText_correctly() = run {
        MainScreen {
            centerButton {
                click()
            }
            centerText {
                hasText("Hello Button!")
            }
        }
    }

    @Test
    fun final_mainScreen_test_case_1() = run {
        step("MainScreen이 launch 되었을때 올바른 초기 텍스트가 centerText에 설정되어 있어야 한다") {
            MainScreen {
                centerText {
                    isVisible()
                    hasText("Hello World!")
                }
            }
        }

        step("MainScreen의 centerButton을 클릭하면 centerText가 올바른 값으로 업데이트 되어야 한다") {
            MainScreen {
                centerButton {
                    click()
                }
                centerText {
                    hasText("Hello Button!")
                }
            }
        }
    }
}