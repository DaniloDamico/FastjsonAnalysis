import com.alibaba.fastjson.annotation.JSONField;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class MapTest extends TestCase {

    private JSONObject obj;
    private String key1;
    private Object value1;
    private String key2;
    private Object value2;
    private String expected;

    private JSONObject objNull;
    private String keyNull;
    private Object valueNull;
    private String expectedNull;

    private String keyOnJSONField;
    private String valueOnJSONField;
    private String expectedOnJSONField;

    @Before
    public void initialize(){
        obj = new JSONObject(true);
        objNull = new JSONObject(true);
    }

    public MapTest(String key1, Object value1, String key2, Object value2, String expected, String keyNull, Object valueNull, String expectedNull, String keyOnJSONField, String valueOnJSONField, String expectedOnJSONField){
        configure_no_sort(key1, value1, key2, value2, expected);
        configure_null(keyNull, valueNull, expectedNull);
        configure_onJSONField(keyOnJSONField, valueOnJSONField, expectedOnJSONField);
    }

    // mettere test
    @Parameterized.Parameters
    public static Collection parametersCollection() {
       return Arrays.asList(new Object[][] {
          { "name", "jobs", "id", 33, "{'name':'jobs','id':33}", "name", "value", "{\"name\":\"value\"}", "Ariston", null, "{\"map\":{\"Ariston\":null}}"},
          { "", "", "id", 33, "{'':'','id':33}", "name", null, "{\"name\":null}", null, null, "{\"map\":{null:null}}"},
          { null, null, "id", 0, "{'id':0}", null, null, "{null:null}", "", "", "{\"map\":{\"\":\"\"}}"},
          { "name", "jobs", "id", -33, "{'name':'jobs','id':-33}", "name", null, "{\"name\":null}", "Ariston", "value", "{\"map\":{\"Ariston\":\"value\"}}"}
       });
    }

    public void configure_no_sort(String key1, Object value1, String key2, Object value2, String expected){
        this.key1 = key1;
        this.value1 = value1;
        this.key2 = key2;
        this.value2 = value2;
        this.expected = expected;
    }
    
    @Test
    public void test_no_sort(){
            obj.put(key1, value1);
            obj.put(key2, value2);
            String text = toJSONString(obj);
            Assert.assertEquals(expected, text);
    }

    public void configure_null(String keyNull, Object valueNull, String expectedNull){
        this.keyNull = keyNull;
        this.valueNull = valueNull;
        this.expectedNull = expectedNull;
    }
    
    @Test
    public void test_null() throws Exception {
        objNull.put(keyNull, valueNull);
        String text = JSON.toJSONString(objNull, SerializerFeature.WriteMapNullValue);
        Assert.assertEquals(expectedNull, text);
    }
    
    public static final String toJSONString(Object object) {
        SerializeWriter out = new SerializeWriter();

        try {
            JSONSerializer serializer = new JSONSerializer(out);
            serializer.config(SerializerFeature.SortField, false);
            serializer.config(SerializerFeature.UseSingleQuotes, true);

            serializer.write(object);

            return out.toString();
        } catch (StackOverflowError e) {
            throw new JSONException("maybe circular references", e);
        } finally {
            out.close();
        }
    }

    public void configure_onJSONField(String keyOnJSONField, String valueOnJSONField, String expectedOnJSONField){
        this.keyOnJSONField = keyOnJSONField;
        this.valueOnJSONField = valueOnJSONField;
        this.expectedOnJSONField = expectedOnJSONField;
    }

    @Test
    public void test_onJSONField() {
        Map<String, String> map = new HashMap();
        map.put(keyOnJSONField, valueOnJSONField);
        MapNullValue mapNullValue = new MapNullValue();
        mapNullValue.setMap( map );
        String json = JSON.toJSONString( mapNullValue );
        assertEquals(expectedOnJSONField, json);
    }

    class MapNullValue {
        @JSONField(serialzeFeatures = {SerializerFeature.WriteMapNullValue})
        private Map map;

        public Map getMap() {
            return map;
        }

        public void setMap( Map map ) {
            this.map = map;
        }
    }

}