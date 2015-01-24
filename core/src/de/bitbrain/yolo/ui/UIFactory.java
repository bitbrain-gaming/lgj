package de.bitbrain.yolo.ui;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.Colors;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.graphics.GraphicsFactory;

public final class UIFactory {

	public static Button generatePrimaryButton(String text, ClickListener listener) {
		final TextButtonStyle style = new TextButtonStyle();
		style.font = SharedAssetManager.get(Assets.FNT_REGULAR, BitmapFont.class);
		style.up = GraphicsFactory.getNinePatchDrawable(Assets.TEX_PATCH_BORDER, 15);
		style.over = GraphicsFactory.getNinePatchDrawable(Assets.TEX_PATCH_BORDER, 15, Colors.MAIN);
		style.down = style.over;		
		style.fontColor = Color.WHITE;
		style.overFontColor = Colors.MAIN;
		TextButton button = new TextButton(text, style);
		button.addCaptureListener(listener);
		button.addCaptureListener(new ClickListener() {
			@Override
			public void enter(InputEvent event, float x, float y, int pointer,
					Actor fromActor) {
				super.enter(event, x, y, pointer, fromActor);
			}
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Sound clickSound = SharedAssetManager.get(Assets.SND_CLICK, Sound.class);
				clickSound.play(1f);
			}
		});
		return button;
	}
}
