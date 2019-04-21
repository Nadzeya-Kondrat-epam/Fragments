package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Activity which contains [FragmentA] and [FragmentB]
 *
 */
class MainActivity : AppCompatActivity(), CounterClickListener {

    private var counter = 0

    private val isPortrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counter = savedInstanceState?.getInt(COUNTER_KEY, 0) ?: 0
        if (isPortrait) {
            setUpPortraitView()
        } else {
            setUpLandView()
        }
    }

    override fun onCounterClicked() {
        counter++
        // remove last saved in backstack fragment to replace it with the new one
        val fragmentHolder = if (isPortrait) {
            R.id.fragment_holder
        } else {
            R.id.fragment_holder_b
        }
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(fragmentHolder, FragmentB.newInstance(counter))
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!isPortrait) {
            finish()
        }
    }

    private fun setUpPortraitView() {
        val fragementB = supportFragmentManager.findFragmentByTag(FRB_TAG) as? FragmentB
        supportFragmentManager.popBackStack()
        if (fragementB != null && counter != 0) {
            fragementB.arguments?.putInt(COUNTER_KEY, counter)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, FragmentB.newInstance(counter), FRB_TAG)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, FragmentA())
                .commit()
        }
    }

    private fun setUpLandView() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_holder_a, FragmentA())
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_holder_b, FragmentB.newInstance(counter), FRB_TAG)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(COUNTER_KEY, counter)
        super.onSaveInstanceState(outState)
    }

    private companion object {
        private val COUNTER_KEY = "1001"
        private val FRB_TAG = "FrB"
    }
}
