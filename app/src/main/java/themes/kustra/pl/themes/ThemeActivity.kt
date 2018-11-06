package themes.kustra.pl.themes

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import themes.kustra.pl.themes.utils.Constants.Companion.BLUE
import themes.kustra.pl.themes.utils.Constants.Companion.THEME_COLOR
import themes.kustra.pl.themes.utils.Constants.Companion.GREEN
import themes.kustra.pl.themes.utils.Constants.Companion.RED

class ThemeActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                saveTheme(RED)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                saveTheme(GREEN)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                saveTheme(BLUE)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getSavedTheme())
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun getSavedTheme(): Int {
        val theme = getPreferences(Context.MODE_PRIVATE).getString(THEME_COLOR, RED)
        return when (theme) {
            RED -> R.style.DefaultAppTheme
            BLUE -> R.style.DefaultAppTheme_BlueTheme
            GREEN -> R.style.DefaultAppTheme_GreenTheme
            else -> R.style.DefaultAppTheme
        }
    }

    private fun saveTheme(color: String) {
        val editor = getPreferences(Activity.MODE_PRIVATE).edit()
        editor.putString(THEME_COLOR, color)
        editor.apply()
        recreate()
    }
}
