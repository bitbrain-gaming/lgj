package de.bitbrain.yolo.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.utils.Array;

import de.bitbrain.yolo.Assets;
import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.core.GameObject;

public class ParticleRenderer {
	
	private Map<String, ParticleEffectPool> pools;
	
	private Map<GameObject, PooledEffect> effects;
	
	private Map<GameObject, String> mapping;
	
	private Array<PooledEffect> renderTargets;

	public ParticleRenderer() {
		renderTargets = new Array<PooledEffect>();
		pools = new HashMap<String, ParticleEffectPool>();
		effects = new HashMap<GameObject, PooledEffect>();
		mapping = new HashMap<GameObject, String>();
	}
	
	public PooledEffect getEffect(GameObject object) {
		PooledEffect effect = effects.get(object);
		if (effect != null) {
			return effect;
		} else if (mapping.containsKey(object)) {
			String id = mapping.get(object);
			ParticleEffectPool pool = pools.get(id);
			effect = pool.obtain();
			effects.put(object, effect);
			return effect;
		} else {
			return null;
		}
	}
	
	public void applyParticleEffect(GameObject object, String id) {
		pools.put(id, new ParticleEffectPool(SharedAssetManager.get(Assets.PRT_BLUE_FLAME, ParticleEffect.class), 10, 100));
		mapping.put(object, id);
		PooledEffect effect = getEffect(object);
		if (!renderTargets.contains(effect, false)) {
			renderTargets.add(effect);
		}
	}
	
	public void updateAndRender(float delta, Batch batch) {
		for (PooledEffect e : renderTargets) {
			e.draw(batch, delta);
		}
	}
}
