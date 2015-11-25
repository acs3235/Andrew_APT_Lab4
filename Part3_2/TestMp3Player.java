package Part3_2;

/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.  All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import static org.easymock.EasyMock.*;
import junit.framework.*;

import java.util.ArrayList;

import Part3.Jukebox;

public class TestMp3Player extends TestCase {
	private Mp3Player mockMP3;
	private Mp3Player mockMP3_control;

  protected Mp3Player mp3;
  protected ArrayList list = new ArrayList();

  public void setUp() {
    //mp3 = new MockMp3Player();

    list = new ArrayList();
    list.add("Bill Chase -- Open Up Wide");
    list.add("Jethro Tull -- Locomotive Breath");
    list.add("The Boomtown Rats -- Monday");
    list.add("Carl Orff -- O Fortuna");
    
 // Create a control handle to the Mock object
    mockMP3_control = createMock(Mp3Player.class);

    // And create the mock object itself
    mp3 = mockMP3_control;
  }

  public void testPlay() {
    
  	
	
    expect(mp3.isPlaying()).andReturn(false).andReturn(true);
    expect(mp3.currentPosition()).andReturn(1.0).andReturn(2.0).andReturn(0.0);
    mp3.play();
    expectLastCall();
    mp3.pause();
    expectLastCall();
    mp3.stop();
    expectLastCall();
    mp3.loadSongs(list);
    expectLastCall();
    replay( mockMP3_control );
    
    mp3.loadSongs(list);
    assertFalse(mp3.isPlaying());
    mp3.play();
    assertTrue(mp3.isPlaying());
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.pause();
    assertTrue(mp3.currentPosition() != 0.0);
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);

  }

  public void testPlayNoList() {
	  
	expect(mp3.isPlaying()).andReturn(false).times(4);
    expect(mp3.currentPosition()).andReturn(0.0).times(3);
    mp3.play();
    expectLastCall();
    mp3.pause();
    expectLastCall();
    mp3.stop();
    expectLastCall();
    replay( mockMP3_control );

    // Don't set the list up
    assertFalse(mp3.isPlaying());
    mp3.play();
    assertFalse(mp3.isPlaying());
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    mp3.pause();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
    mp3.stop();
    assertEquals(mp3.currentPosition(), 0.0, 0.1);
    assertFalse(mp3.isPlaying());
  }

  public void testAdvance() {
	
	expect(mp3.isPlaying()).andReturn(true).times(3);
    expect(mp3.currentSong()).andReturn((String) list.get(0)).andReturn((String) list.get(1)).andReturn((String) list.get(2)).andReturn((String) list.get(1)).andReturn((String) list.get(2)).andReturn((String) list.get(3)).andReturn((String) list.get(3));
    mp3.next();
    expectLastCall().times(5);
    mp3.play();
    expectLastCall();
    mp3.prev();
    expectLastCall().times(2);
    mp3.loadSongs(list);
    expectLastCall();
    replay( mockMP3_control );
	  

    mp3.loadSongs(list);

    mp3.play();

    assertTrue(mp3.isPlaying());

    mp3.prev();
    assertEquals(mp3.currentSong(), list.get(0));
    assertTrue(mp3.isPlaying());

    mp3.next();
    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.prev();

    assertEquals(mp3.currentSong(), list.get(1));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(2));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    mp3.next();
    assertEquals(mp3.currentSong(), list.get(3));
    assertTrue(mp3.isPlaying());
  }

}