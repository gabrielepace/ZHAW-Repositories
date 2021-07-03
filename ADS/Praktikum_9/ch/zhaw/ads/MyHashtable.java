package ch.zhaw.ads;

import java.util.*;

/**
 * ADS FS2019
 * 
 * Praktikum 8 
 * Aufgabe 3 – Eigene Hashtable &
 * Aufgabe 4 - Überlauf
 * 
 * @author Gabriele Pace (pacegab1), Omar Shakir (shakioma)
 */

public class MyHashtable<K, V> implements java.util.Map<K, V> {
	private K[] keys = (K[]) new Object[10];
	private V[] values = (V[]) new Object[10];

	private int hash(Object k) {
		int h = Math.abs(k.hashCode());
		return h % keys.length;
	}

	public MyHashtable(int size) {
		// to be done
		K[] keys = (K[]) new Object[size];
		V[] values = (V[]) new Object[size];
	}

	// Removes all mappings from this map (optional operation).
	public void clear() {
		// to be done
		for (int i = 0; i < keys.length; i++) {
			keys[i] = null;
			values[i] = null;
		}
	}

	// Associates the specified value with the specified key in this map (optional
	// operation).
	public V put(K key, V value) {
		// to be done
		int h = hash(key);
		while (keys[h] != null && keys[h] != key) {
			h = hash(h + 1);
			if (key.equals(keys[h])) {
				values[h] = value;
				return value;
			}
		}
		keys[h] = key;
		values[h] = value;
		return value;
	}

	// Returns the value to which this map maps the specified key.
	public V get(Object key) {
		// to be done
		for (int i = hash(key); keys[i] != null; i = hash(i + 1)) {
			if (keys[i].equals(key)) {
				return values[i];
			}
		}
		return null;
	}

	// Returns true if this map contains no key-value mappings.
	public boolean isEmpty() {
		// to be done
		return this.size() <= 0;
	}

	// Removes the mapping for this key from this map if present (optional
	// operation).
	public V remove(Object key) {
		// to be done (Aufgabe 3)
		int h = hash(key);
		if (!containsKey(key))
			return null;
		while (!key.equals(keys[h])) {
			h = hash(h + 1);
		}
		keys[h] = null;
		values[h] = null;
		h = hash(h + 1);
		while (keys[h] != null) {
			K keyToRehash = keys[h];
			V valToRehash = values[h];
			keys[h] = null;
			values[h] = null;
			put(keyToRehash, valToRehash);
			h = hash(h + 1);
		}
		return null;
	}

	// Returns the number of key-value mappings in this map.
	public int size() {
		// to be done
		int size = 0;
		for (int i = 0; i < keys.length; i++) {
			size = keys[i] != null ? size + 1 : size;
		}
		return size;
	}

	// =======================================================================
	// Returns a set view of the keys contained in this map.
	public Set keySet() {
		throw new UnsupportedOperationException();
	}

	// Copies all of the mappings from the specified map to this map (optional
	// operation).
	public void putAll(Map t) {
		throw new UnsupportedOperationException();
	}

	// Returns a collection view of the values contained in this map.
	public Collection values() {
		throw new UnsupportedOperationException();
	}

	// Returns true if this map contains a mapping for the specified key.
	public boolean containsKey(Object key) {
		return this.get(key) != null;
	}

	// Returns true if this map maps one or more keys to the specified value.
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	// Returns a set view of the mappings contained in this map.
	public Set entrySet() {
		throw new UnsupportedOperationException();
	}

	// Compares the specified object with this map for equality.
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	// Returns the hash code value for this map.
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	@Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("{ ");
    	 for(int i = 0; i<keys.length;i++) {
    		if(keys[i]!=null) {
	       	  	s.append(keys[i]);
	       	  	s.append("=");
	       	  	s.append(values[i]);
    		}
         }
     	s.append("}");
     	return s.toString();
    }

}