package de.bitbrain.yolo.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.graphics.GraphicsFactory;

public final class UIFactory {

	public static Button generatePrimaryButton(String text, ClickListener listener) {
		TextButtonStyle style = new TextButtonStyle();
		style.font = SharedAssetManager.get(Assets.FNT_REGULAR, BitmapFont.class);
		style.up = GraphicsFactory.getNinePatchDrawable(Assets.TEX_PATCH_BORDER, 15);
		style.down = style.up;
		TextButton button = new TextButton(text, style);
		button.addCaptureListener(listener);
		return button;
	}
}
