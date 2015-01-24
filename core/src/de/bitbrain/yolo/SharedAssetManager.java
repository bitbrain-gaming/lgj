/*
 * Craft - Crafting game for Android, PC and Browser.
 * Copyright (C) 2014 Miguel Gonzalez
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package de.bitbrain.yolo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import de.bitbrain.yolo.loader.ParticleLoader;

public class SharedAssetManager {

	private static AssetManager instance = null;
	
	private SharedAssetManager() { }
	
	public static AssetManager getInstance() {
		
		if (instance == null) {
			loadInternal();
		}
		
		return instance;
	}
	
	public static <T> T get(String s, Class<T> clss) {
		return getInstance().get(s, clss);
	}
	
	public static boolean isLoaded(String s) {
		return getInstance().isLoaded(s);
	}
	
	public static void dispose() {
		if (instance != null) {
			Gdx.app.log("DISPOSE", "Unload resources..");
			getInstance().dispose();
			instance = null;
		}
	}
	
	private static void loadInternal() {
		
		if (Gdx.files == null) {
			throw new RuntimeException("LibGDX is not initialized yet!");
		}
		
		if (Gdx.files.isLocalStorageAvailable()) {		
			instance = new AssetManager();		
			instance.setLoader(ParticleEffect.class, new ParticleLoader(new InternalFileHandleResolver()));
		}
	}
}
