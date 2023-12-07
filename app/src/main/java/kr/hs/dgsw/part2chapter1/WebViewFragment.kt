package kr.hs.dgsw.part2chapter1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import kr.hs.dgsw.part2chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val url: String) : Fragment() {

    var listener: OnTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebviewBinding

    companion object {
        const val SHARED_PREFERENCES = "WEB_HISTORY"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar, { url ->
            activity?.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        })
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)

        binding.lastButton.setOnClickListener {
//            마지막 화의 url을 sharedPreFerences에 저항하고 버튼이 클릭되었을때 webViewUrl에 넣음
            val sharedPreferences =
                activity?.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position", "")
            // null일떄
            if (url.isNullOrEmpty()) {
                Toast.makeText(context, "마지막 회차가 없습니다", Toast.LENGTH_SHORT).show()
            }
            // null이 아닐때
            else {
                binding.webView.loadUrl(url)
            }


        }
        binding.changeButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)
            dialog.setView(editText)
            dialog.setPositiveButton("저장") { _, _ ->
                // TODO 저장기능
                activity?.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }
            }
            dialog.setNegativeButton("취소") { dialogInterFace, _ ->
                dialogInterFace.cancel()

            }
            dialog.show()
        }
    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }

}


interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}