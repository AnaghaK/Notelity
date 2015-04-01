package com.rohangadad.notelity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Rohan on 3/30/15.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class NotesDataSource {

    private static final String PREFKEY = "notes";
    private SharedPreferences notePrefs;

    public NotesDataSource(MainActivity context) {
        notePrefs = context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
    }

    public List<NoteItem> findAll() {

        Map<String, ?> notesMap = notePrefs.getAll();

        SortedSet<String> keys = new TreeSet<String>(notesMap.keySet());

        List<NoteItem> noteList = new ArrayList<NoteItem>();
        for (String key : keys) {
            NoteItem note = new NoteItem();
            note.setKey(key);
            note.setText((String) notesMap.get(key));
            noteList.add(note);
        }

        return noteList;

    }

    public boolean update(NoteItem note) {

        SharedPreferences.Editor editor = notePrefs.edit();
        editor.putString(note.getKey(), note.getText());
        editor.commit();
        return true;
    }

    public boolean remove(NoteItem note) {

        if (notePrefs.contains(note.getKey())) {
            SharedPreferences.Editor editor = notePrefs.edit();
            editor.remove(note.getKey());
            editor.commit();
        }

        return true;
    }


}