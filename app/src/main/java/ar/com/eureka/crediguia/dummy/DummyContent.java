package ar.com.eureka.crediguia.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static int COUNT = 0;

   /* static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            System.out.println("CargarContenido "+i);
            addItem(createDummyItem(i));
        }
    }*/

    public static void cargarContenido(List<HashMap> lista){
        if(lista != null){
            ITEMS = new ArrayList<DummyItem>();
            for (int i = 0; i < lista.size(); i++) {
                HashMap un = lista.get(i);
                System.out.println("CargarContenido "+un);
                addItem(createDummyItem(un));
            }
            COUNT = lista.size();
        }

    }
    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position), new HashMap());
    }

    private static DummyItem createDummyItem(HashMap uno) {
        return new DummyItem(uno.get("id").toString(), uno.get("descripcion").toString(), makeDetails(uno.get("detalle").toString()), uno);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
    private static String makeDetails(String detalle) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nDetalle: \n").append(detalle);
        /*for (int i = 0; i < 10; i++) {
            builder.append("\nMore details information here.");
        }*/
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;
        public final HashMap<String,String> info;

        public DummyItem(String id, String content, String details, HashMap<String, String> info) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.info = info;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
