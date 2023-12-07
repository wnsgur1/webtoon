package kr.hs.dgsw.part2chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebtoonWebViewClient(
    private val progressBar : ProgressBar,
    private val saveDate: (String)-> Unit
): WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

//        // request에 네이버 웹툰 주소가 있으면 false)리턴 아니면 true리턴
//        if(request != null && request.url.toString().contains("comic.naver.com")){
//            return false
//        }else{
//            return true
//        }

        // request가 널이 아니고 웹툰 디테일을 포함하고 있으면 실행
        if(request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")){
            saveDate.invoke(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)


    }



    // 페이지가 시작이 끝날때
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // 페이지가 시작되었으니까 로딩을 안보이게
        progressBar.visibility = View.GONE

    }
    // 페이지가 시작될때
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        // 페이지가 시작되고 있으니 로딩을 보이게
        progressBar.visibility = View.VISIBLE
    }


}