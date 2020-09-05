package com.maulikpract.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.maulikpract.BR

abstract class BaseActivity<BINDING : ViewDataBinding, VIEWMODEL : BaseViewModel, STATE : BaseState>(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {
    protected abstract val viewModel: VIEWMODEL
    protected lateinit var binding: BINDING
    protected val state: STATE by lazy {
        viewModel.provieState() as STATE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        observeViewState(state)
    }

    abstract fun observeViewState(state: STATE)

    private fun initializeBinding() {
        DataBindingUtil.setContentView<BINDING>(this, layoutRes).apply {
            binding = this
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel,viewModel)
            setVariable(BR.state,state)
            executePendingBindings()
        }
    }

    /**
     * Display Toast
     * @param message String
     * @param duration Int
     */
    fun toast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }

    /**
     * Display Toast
     * @param message Int : String resource ID
     */
    fun toast(@StringRes message: Int) {
        toast(getString(message))
    }
}