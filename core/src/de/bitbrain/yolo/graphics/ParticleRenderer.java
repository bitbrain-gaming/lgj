package de.bitbrain.yolo.graphics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter.ScaledNumericValue;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import de.bitbrain.yolo.SharedAssetManager;
import de.bitbrain.yolo.core.GameObject;

public class ParticleRenderer {
	
	private Map<String, ParticleEffectPool> pools;
	
	private Map<GameObject, PooledEffect> effects;
	
	private Map<GameObject, String> mapping;
	
	private Map<PooledEffect, GameObject> objects;
	
	private Map<GameObject, Vector2> offsets;
	
	private List<PooledEffect> singleEffects;

	public ParticleRenderer() {
		singleEffects = new CopyOnWriteArrayList<PooledEffect>();
		pools = new HashMap<String, ParticleEffectPool>();
		effects = new HashMap<GameObject, PooledEffect>();
		mapping = new HashMap<GameObject, String>();
		offsets = new HashMap<GameObject, Vector2>();
		objects = new HashMap<PooledEffect, GameObject>();
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
	
	public void applyParticleEffect(String id, float x, float y) {
		ensurePool(id);
		ParticleEffectPool pool = pools.get(id);
		PooledEffect effect = pool.obtain();
		effect.setPosition(x, y);
		singleEffects.add(effect);
		
	}
	
	public void applyParticleEffect(GameObject object, String id, float offsetX, float offsetY) {
		ensurePool(id);
		mapping.put(object, id);
		offsets.put(object, new Vector2(offsetX, offsetY));
		PooledEffect effect = getEffect(object);
		objects.put(effect, object);
	}
	
	public void updateAndRender(float delta, Batch batch) {
		for (Entry<PooledEffect, GameObject> effectEntry : objects.entrySet()) {
			float x = effectEntry.getValue().getPosition().x + offsets.get(effectEntry.getValue()).x;
			float y = effectEntry.getValue().getPosition().y + offsets.get(effectEntry.getValue()).y;
			GameObject o = effectEntry.getValue();
			Array<ParticleEmitter> emitters = effectEntry.getKey().getEmitters();
			
			for (int i = 0; i < emitters.size; ++i) {
				ScaledNumericValue val = emitters.get(i).getAngle();
				float h1 = o.getAngle() - 90f;
				float h2 = o.getAngle() - 90f;
				val.setHigh(h1, h2);
				val.setLow(h1);
			}
			
			effectEntry.getKey().setPosition(x, y);
			effectEntry.getKey().draw(batch, delta);
		}
		for (PooledEffect single : singleEffects) {
			single.draw(batch, delta);
			if (single.isComplete()) {
				single.free();
				singleEffects.remove(single);
			}
		}
	}

	public void remove(PooledEffect e) {
		e.free();
		objects.remove(e);
	}
	
	private void ensurePool(String id) {
		if (!pools.containsKey(id)) {
			pools.put(id, new ParticleEffectPool(SharedAssetManager.get(id, ParticleEffect.class), 10, 100));
		}
	}
}
