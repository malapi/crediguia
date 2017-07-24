package ar.com.eureka.crediguia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import ar.com.eureka.crediguia.R;
import ar.com.eureka.crediguia.dummy.DummyContent;

/**
 * A placeholder fragment containing a simple view.
 */
public class PdfViewerActivityFragment extends Fragment {

    private ProgressDialog progressBar;

    public PdfViewerActivityFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate Fragment "+getArguments());
        if (getArguments() != null && getArguments().containsKey("url")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Activity activity = this.getActivity();
            activity.setTitle(getArguments().getString("titulo"));

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pdf_viewer, container, false);
        WebView myWebView = (WebView) rootView.findViewById(R.id.webView_pdf);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

        progressBar = ProgressDialog.show(getContext(), "Estamos Cargando la Información", "Cargando...");


        myWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                //Log.i(TAG, "Finished loading URL: " +url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //Log.e(TAG, "Error: " + description);
                Toast.makeText(getContext(), "Error al cargar la información! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        //getArguments().getString("url")
        System.out.println("onCreateView " + getArguments());
        if(getArguments() != null && getArguments().containsKey("url")){
            myWebView.loadUrl(getArguments().getString("url"));
        } else {
            //myWebView.loadUrl("https://drive.google.com/viewer?url=http://www.tra.org.bh/media/document/sample1.pdf");
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }




        return rootView;
    }
}
