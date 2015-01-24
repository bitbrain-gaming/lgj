package de.bitbrain.yolo.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.Colors;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.core.Player;
import de.bitbrain.yolo.graphics.GraphicsFactory;

public class PlayerWidget extends Table {
	
	private Player player;
	private ProgressBar bar;
	
	public PlayerWidget(Player player) {
		setFillParent(true);
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = SharedAssetManager.get(Assets.FNT_REGULAR, BitmapFont.class);
		labelStyle.fontColor = new Color(1f, 1f, 1f, 0.7f);
		Label name = new Label("Player", labelStyle);
		name.setFontScale(0.4f);
		this.player = player;
		left().add(name).padLeft(20f).row();;
		
		ProgressBarStyle progressStyle = new ProgressBarStyle();
		progressStyle.background = GraphicsFactory.getNinePatchDrawable(Assets.TEX_PATCH_BORDER, 15, Color.WHITE);
		progressStyle.knobAfter = GraphicsFactory.getNinePatchDrawable(Assets.TEX_PATCH_FILL, 15, Colors.MAIN);
		bar = new ProgressBar(0, 100, 1f, true, progressStyle);
		add(bar);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		bar.setValue(player.getMinShield());
	}
}
