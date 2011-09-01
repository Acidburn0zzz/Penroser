/*
 * [The "BSD licence"]
 * Copyright (c) 2011 Ben Gruver
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.Penroser;

import afzkl.development.mColorPicker.views.ColorPickerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PenroserColorPicker extends PenroserBaseActivity {
    private ColorPickerView colorPicker;
    private PenroserGLView penroserView;

    private String sharedPrefName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefName = getIntent().getExtras().getString("sharedPrefName");

        setContentView(R.layout.color_picker);

        colorPicker = (ColorPickerView)findViewById(R.id.color_picker);
        penroserView = (PenroserGLView)findViewById(R.id.penroser_view);

        final int rhombusId = getIntent().getExtras().getInt("rhombus");
        final int color = getIntent().getExtras().getInt("color");

        colorPicker.setColor(color);

        colorPicker.setOnColorChangedListener(new ColorPickerView.OnColorChangedListener() {
            public void onColorChanged(int color) {
                HalfRhombusType rhombusType = PenroserApp.mapRhombusIdToRhombusType(rhombusId);

                penroserView.setColor(rhombusType, color);
            }
        });

        Button okButton = (Button)findViewById(R.id.ok);

        setResult(-1);

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(colorPicker.getColor());
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        if (penroserView != null) {
            penroserView.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (penroserView != null) {
            penroserView.onPause();
        }
        super.onPause();
    }

    @Override
    public String getSharedPreferenceName() {
        return sharedPrefName;
    }
}