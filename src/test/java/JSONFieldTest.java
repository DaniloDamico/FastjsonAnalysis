import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import junit.framework.TestCase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

@RunWith(Parameterized.class)
public class JSONFieldTest extends TestCase {

    private int id;
    private String name;
    private String expected;

    public JSONFieldTest(int id, String name, String expected){
        configure(id, name, expected);
    }

    public void configure(int id, String name, String expected){
        this.id = id;
        this.name = name;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection parametersCollection() {
       return Arrays.asList(new Object[][] {
          { 123, "xx", "{\"id\":123}" },
          { -7, "xx", "{\"id\":-7}" },
          { 0, null, "{\"id\":0}" },
          { -123, "", "{\"id\":-123}" }
       });
    }

    @Test
	public void test_jsonField() throws Exception {
        VO vo = new VO();

		vo.setId(id);
		vo.setName(name);
		
		String text = JSON.toJSONString(vo);
		Assert.assertEquals(expected, text);
	}

	public static class VO {
		private int id;
		
		@JSONField(serialize=false)
		private String name;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
