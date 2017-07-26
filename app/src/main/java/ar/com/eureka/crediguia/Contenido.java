package ar.com.eureka.crediguia;

import org.json.JSONObject;

/**
 * Created by malapi on 25/07/17.
 */

class Contenido {

    //static String resumenes = "{RequestsStatusOK:true,RequestsStatusObs:null,idCuenta:110469,Resumenes:[{idResumen:3364474,Archivo:P\\/R\\/3364474.pdf,Vencimiento:10\\/08\\/2017,Periodo:201707,TotalAPagar:21174.82,TotalAPagar2doVenc:22291.64},{idResumen:3343928,Archivo:P\\/R\\/3343928.pdf,Vencimiento:10\\/07\\/2017,Periodo:201706,TotalAPagar:19827.15,TotalAPagar2doVenc:20895.42},{idResumen:3323236,Archivo:P\\/R\\/3323236.pdf,Vencimiento:10\\/06\\/2017,Periodo:201705,TotalAPagar:16866.6,TotalAPagar2doVenc:17719.38},{idResumen:3302478,Archivo:P\\/R\\/3302478.pdf,Vencimiento:10\\/05\\/2017,Periodo:201704,TotalAPagar:20531.24,TotalAPagar2doVenc:21617.96},{idResumen:3281569,Archivo:P\\/R\\/3281569.pdf,Vencimiento:10\\/04\\/2017,Periodo:201703,TotalAPagar:19674.1,TotalAPagar2doVenc:20666.88},{idResumen:3260408,Archivo:P\\/R\\/3260408.pdf,Vencimiento:10\\/03\\/2017,Periodo:201702,TotalAPagar:19905.66,TotalAPagar2doVenc:20976.34}]}";
    static String resumenes = "{\"RequestsStatusOK\":true,\"RequestsStatusObs\":null,\"idCuenta\":110469,\"Resumenes\":[{\"idResumen\":3364474,\"Archivo\":\"P\\/R\\/3364474.pdf\",\"Vencimiento\":\"10\\/08\\/2017\",\"Periodo\":\"201707\",\"TotalAPagar\":21174.82,\"TotalAPagar2doVenc\":22291.64},{\"idResumen\":3343928,\"Archivo\":\"P\\/R\\/3343928.pdf\",\"Vencimiento\":\"10\\/07\\/2017\",\"Periodo\":\"201706\",\"TotalAPagar\":19827.15,\"TotalAPagar2doVenc\":20895.42},{\"idResumen\":3323236,\"Archivo\":\"P\\/R\\/3323236.pdf\",\"Vencimiento\":\"10\\/06\\/2017\",\"Periodo\":\"201705\",\"TotalAPagar\":16866.6,\"TotalAPagar2doVenc\":17719.38},{\"idResumen\":3302478,\"Archivo\":\"P\\/R\\/3302478.pdf\",\"Vencimiento\":\"10\\/05\\/2017\",\"Periodo\":\"201704\",\"TotalAPagar\":20531.24,\"TotalAPagar2doVenc\":21617.96},{\"idResumen\":3281569,\"Archivo\":\"P\\/R\\/3281569.pdf\",\"Vencimiento\":\"10\\/04\\/2017\",\"Periodo\":\"201703\",\"TotalAPagar\":19674.1,\"TotalAPagar2doVenc\":20666.88},{\"idResumen\":3260408,\"Archivo\":\"P\\/R\\/3260408.pdf\",\"Vencimiento\":\"10\\/03\\/2017\",\"Periodo\":\"201702\",\"TotalAPagar\":19905.66,\"TotalAPagar2doVenc\":20976.34}]}";
//
    public JSONObject getResumenes(){
        JSONObject data = null;
        try {
            data = new JSONObject(resumenes); //Convert from string to object, can also use JSONArray
            System.out.println("Aca +"+data.toString());
        } catch (Exception ex) {}
        return data;
    }

    public static String getResumenes_String(){

        return resumenes;
    }
}
