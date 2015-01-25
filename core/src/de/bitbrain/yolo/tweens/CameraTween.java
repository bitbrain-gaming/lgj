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

package de.bitbrain.yolo.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Camera;

public class CameraTween implements TweenAccessor<Camera> {

	public static final int X_POS = 1;

	public static final int Y_POS = 2;

	@Override
	public int getValues(Camera target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case X_POS:
			returnValues[0] = target.position.x;
			return 1;
		case Y_POS:
			returnValues[0] = target.position.y;
			return 1;
		}
		return 0;
	}

	@Override
	public void setValues(Camera target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case X_POS:
			target.position.x = newValues[0];
			break;
		case Y_POS:
			target.position.y = newValues[0];
			break;
		}
	}

}
