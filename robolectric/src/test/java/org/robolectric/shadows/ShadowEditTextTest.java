package org.robolectric.shadows;

import android.widget.EditText;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.R;
import org.robolectric.TestRunners;
import org.robolectric.res.Attribute;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.RuntimeEnvironment.application;
import static org.robolectric.Shadows.shadowOf;

@RunWith(TestRunners.WithDefaults.class)
public class ShadowEditTextTest {
  private EditText editText;

  @Before
  public void setup() {
    List<Attribute> attributes = new ArrayList<Attribute>();
    attributes.add(new Attribute("android:attr/maxLength", "5", R.class.getPackage().getName()));
    RoboAttributeSet attributeSet = new RoboAttributeSet(attributes, shadowOf(application.getResources()).getResourceLoader());
    editText = new EditText(application, attributeSet);
  }

  @Test
  public void shouldRespectMaxLength() throws Exception {
    editText.setText("0123456678");
    assertThat(editText.getText().toString()).isEqualTo("01234");
  }

  @Test
  public void shouldAcceptNullStrings() {
    editText.setText(null);
    assertThat(editText.getText().toString()).isEqualTo("");
  }
}
