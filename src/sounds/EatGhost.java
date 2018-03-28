package sounds;

import java.applet.AudioClip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
/** <p>An object implementing {@link java.applet.AudioClip java.applet.AudioClip} with the data from pacman_eatghost.wav hard-coded into it.</p>
 * <p>Created with the <a href="http://stephengware.com/projects/soundtoclass">SoundToClass tool</a>, by Stephen G. Ware.</p>
 * @author Stephen G. Ware */
public class EatGhost extends JavaSounds implements AudioClip {
	private byte[] data;
	private AudioFormat format;
	private DataLine.Info lineInfo = null;
	private PlayThread playThread = null;
	private LoopThread loopThread = null;
	private static final long serialVersionUID = 6324;
	private static byte[] data0(){ return new byte[] {-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,127,-128,-128,-80,-42,-37,-35,-38,-39,-42,-44,-47,-48,-51,-52,-55,-56,-59,-60,-62,-64,-66,-67,-69,-69,-71,-71,-72,-73,-73,-74,-75,-75,-76,-76,-77,-78,-79,-80,-82,-83,-84,-85,-87,-88,-89,-90,-91,-92,-93,-94,-96,-96,-98,-98,-99,-100,-101,-102,-103,-104,-105,-106,-107,-107,-108,-109,-110,-112,-113,-114,-115,-115,-116,-117,-118,-119,-119,-120,-121,-121,-122,-122,-123,-123,-124,-124,-125,-125,-126,-126,-127,-127,-128,-128,-128,127,127,127,126,126,126,125,125,125,124,124,124,124,123,123,123,123,123,123,123,121,108,102,100,99,100,100,100,100,100,100,101,101,101,101,102,102,102,102,102,102,103,103,103,103,103,104,104,104,104,104,104,105,105,105,105,105,106,106,106,106,106,106,106,106,106,106,107,106,107,107,107,107,107,107,107,107,107,108,108,108,108,108,108,108,109,109,109,109,109,109,109,109,110,110,110,110,110,110,111,111,111,111,111,111,112,112,112,112,112,112,113,113,119,-114,-105,-103,-103,-104,-104,-105,-106,-106,-107,-108,-108,-109,-110,-110,-111,-112,-112,-113,-113,-114,-114,-115,-115,-116,-117,-117,-118,-118,-118,-119,-119,-120,-120,-121,-121,-122,-122,-122,-123,-124,-124,-125,-126,-127,-127,-128,-128,127,127,127,127,126,126,125,126,124,126,114,82,73,70,70,70,71,72,73,74,75,76,77,78,79,79,80,81,82,83,84,85,85,86,87,88,89,90,90,91,92,93,95,96,96,97,97,98,98,99,99,100,100,101,101,102,102,103,103,104,104,105,105,106,105,107,106,108,105,127,-89,-80,-75,-77,-76,-78,-79,-80,-81,-82,-83,-85,-85,-87,-87,-89,-90,-91,-92,-93,-94,-95,-96,-97,-97,-99,-99,-100,-101,-102,-102,-103,-104,-105,-105,-106,-107,-107,-108,-109,-109,-110,-110,-111,-112,-112,-113,-113,-114,-114,-114,-120,86,58,53,51,52,53,54,56,57,58,60,62,63,65,66,67,68,70,71,72,73,74,76,77,78,79,80,81,81,82,83,84,85,86,86,88,88,90,90,-114,-70,-62,-58,-60,-60,-62,-63,-65,-66,-67,-68,-70,-71,-73,-74,-76,-78,-79,-81,-82,-83,-85,-86,-87,-89,-90,-91,-92,-94,-94,-96,-96,-98,-98,-100,-99,-103,-99,122,60,46,40,41,42,45,45,48,49,51,53,55,56,58,59,61,62,64,66,68,71,73,75,76,78,80,81,82,83,84,85,85,87,87,89,89,92,89,112,-74,-50,-42,-42,-42,-44,-46,-48,-49,-51,-52,-54,-56,-57,-59,-60,-62,-63,-66,-68,-70,-72,-75,-76,-78,-79,-80,-81,-83,-84,-85,-86,-88,-88,-90,-91,-93,-92,-103,81,44,37,34,36,37,39,40,43,44,46,47,50,52,55,56,59,60,63,64,67,68,71,71,73,73,75,74,77,76,81,75,120,-67,-50,-43,-44,-43,-45,-45,-47,-48,-51,-53,-56,-58,-60,-62,-64,-66,-67,-69,-71,-72,-74,-76,-77,-78,-79,-80,-80,-92,86,47,39,36,37,38,40,41,43,45,47,50,52,55,56,59,60,63,63,66,66,69,69,72,71,74,72,77,73,-126,-58,-44,-37,-38,-38,-41,-41,-43,-44,-48,-49,-52,-54,-57,-59,-61,-63,-65,-66,-68,-70,-71,-73,-74,-76,-76,-78,-76,-98,74,41,31,31,30,33,34,36,38,41,43,47,49,51,53,56,57,60,61,64,65,67,68,71,70,73,72,76,74,-113,-49,-37,-30,-32,-32,-34,-35,-38,-39,-43,-46,-49,-51,-54,-57,-59,-62,-63,-66,-67,-69,-71,-73,-73,-76,-75,-79,-75,-109,57,27,17,17,17,19,21,23,25,28,30,32,34,36,38,40,42,43,46,47,50,51,53,54,57,61,-112,-51,-40,-34,-36,-36,-39,-40,-43,-44,-46,-47,-49,-51,-52,-54,-55,-57,-58,-60,-60,-64,-61,-93,72,42,30,30,29,32,32,36,37,40,40,44,44,47,48,51,51,55,54,59,57,63,61,-126,-57,-43,-35,-37,-35,-38,-38,-41,-41,-43,-43,-46,-47,-49,-50,-52,-53,-54,-56,-57,-59,-58,-76,99,65,54,52,52,54,54,56,57,59,59,61,61,64,64,67,67,70,69,72,71,76,72,124,-61,-46,-37,-39,-38,-41,-41,-44,-45,-47,-47,-50,-50,-52,-53,-55,-56,-58,-59,-60,-62,-62,-77,102,67,57,55,55,56,57,59,59,62,62,64,64,66,67,69,69,71,71,74,72,77,73,121,-67,-52,-44,-45,-44,-47,-47,-50,-51,-53,-54,-56,-57,-59,-60,-62,-63,-65,-66,-68,-69,-71,-78,110,68,60,55,56,56,58,58,60,60,62,62,64,64,66,66,69,68,72,67,100,-87,-69,-61,-62,-61,-62,-62,-64,-64,-66,-65,-67,-67,-69,-68,-70,-70,-72,-73,124,79,71,66,68,67,69,69,70,71,72,72,73,74,75,76,77,79,78,94,-100,-76,-68,-68,-68,-69,-70,-72,-72,-74,-74,-77,-77,-79,-79,-82,-81,-85,-82,-114,94,83,77,78,77,79,79,81,81,82,83,84,85}; }
	private static byte[] data1(){ return new byte[] {86,87,88,88,89,93,-122,-98,-93,-91,-92,-92,-93,-94,-94,-95,-96,-96,-97,-98,-98,-99,-99,-101,-100,-111,112,102,99,99,99,100,100,102,103,104,104,105,105,106,106,107,107,108,107,124,-113,-109,-107,-108,-108,-108,-109,-109,-109,-110,-110,-111,-111,-112,-112,-112,-113,-113,-115,127,120,119,118,118,118,118,119,119,119,119,119,119,120,119,120,119,120,119,126,-117,-114,-113,-113,-113,-114,-114,-114,-114,-115,-115,-116,-116,-116,-116,-117,-117,-120,122,116,115,115,115,115,115,115,116,116,116,116,117,116,118,116,126,-108,-101,-99,-99,-99,-99,-100,-100,-101,-101,-102,-102,-104,-103,-105,-103,-120,105,98,94,95,95,95,96,97,97,98,99,99,100,100,102,100,-125,-90,-84,-80,-82,-81,-83,-83,-85,-86,-87,-88,-89,-90,-91,-92,-95,114,79,74,69,71,70,72,72,74,74,77,76,79,78,81,80,92,-104,-74,-69,-66,-68,-68,-71,-71,-74,-74,-76,-76,-78,-78,-82,-80,-99,91,65,59,58,60,61,64,64,67,67,70,70,74,73,79,75,110,-79,-60,-54,-55,-56,-58,-60,-61,-64,-64,-67,-67,-70,-69,-75,-71,-123,69,56,50,52,52,55,56,58,59,61,62,63,65,65,70,68,-128,-67,-57,-51,-53,-53,-56,-57,-60,-61,-62,-64,-65,-67,-69,-71,-76,107,58,49,45,47,48,50,51,53,54,56,59,59,64,60,107,-73,-57,-48,-51,-50,-53,-54,-57,-58,-60,-61,-63,-66,-67,120,57,47,40,44,42,47,46,51,51,56,56,61,60,76,-100,-58,-52,-48,-51,-51,-55,-54,-58,-57,-63,-60,-67,-61,-101,64,33,24,23,24,25,28,29,33,34,38,38,45,41,107,-71,-55,-46,-49,-47,-51,-51,-54,-55,-57,-59,-61,-62,-68,106,46,35,27,30,28,32,31,36,35,39,38,44,40,66,-97,-58,-49,-45,-47,-47,-49,-49,-52,-51,-55,-53,-58,-53,-96,85,65,57,57,56,58,58,60,62,62,65,65,68,67,-123,-60,-49,-42,-44,-42,-45,-45,-48,-48,-51,-51,-54,-54,-66,114,72,64,60,62,61,63,63,66,65,69,67,71,67,97,-83,-58,-50,-49,-49,-50,-51,-52,-54,-54,-57,-57,-61,-58,-108,82,69,60,63,61,64,63,67,65,69,66,71,66,96,-92,-69,-61,-60,-60,-60,-61,-61,-64,-64,-67,-64,-84,106,81,74,72,72,72,73,74,75,75,77,76,89,-105,-77,-70,-68,-69,-69,-71,-71,-73,-74,-76,-75,-87,117,93,88,86,86,87,88,89,90,90,91,92,97,-118,-95,-90,-89,-89,-90,-91,-91,-92,-93,-94,-94,-97,-126,109,106,103,104,104,105,105,105,105,105,105,106,125,-115,-112,-110,-111,-111,-112,-112,-112,-113,-113,-114,-114,-124,121,119,117,118,118,118,118,119,119,119,119,119,-127,-116,-114,-112,-113,-113,-113,-113,-114,-114,-114,-115,-114,-123,121,119,118,118,119,120,120,120,120,120,121,119,-124,-103,-98,-96,-96,-97,-97,-98,-98,-100,-99,-102,-100,-114,110,101,98,98,98,98,99,99,101,100,103,100,119,-96,-84,-81,-81,-81,-82,-83,-84,-86,-86,-88,-86,-105,99,81,75,75,75,76,77,77,80,79,83,80,122,-81,-71,-65,-67,-66,-70,-70,-74,-74,-78,-81,115,73,65,62,63,65,67,68,71,72,74,84,-101,-66,-58,-56,-57,-59,-61,-64,-64,-68,-65,-95,84,61,52,54,53,57,57,62,61,67,63,115,-73,-60,-52,-55,-54,-58,-58,-62,-61,-67,-65,125,65,53,47,50,50,53,54,57,59,61,71,-105,-62,-52,-50,-51,-53,-55,-57,-59,-62,-61,-83,87,52,42,42,43,47,48,53,53,58,55,98,-80,-61,-51,-54,-52,-56,-56,-60,-59,-65,-62,121,45,29,20,23,22,27,28,33,33,38,42,127,-68,-56,-49,-51,-50,-53,-54,-55,-57,-57,-76,88,47,34,31,30,32,31,35,34,38,34,70,-90,-59,-47,-47,-45,-49,-48,-52,-50,-55,-52,-102,83,68,58,60,57,60,58,62,60,64,65,-119,-64,-52,-46,-47,-47,-49,-49,-51,-52,-53,-63,117,72,63,58,59,58,61,60,64,61,83,-96,-64,-56,-53,-54,-53,-56,-54,-59,-54,-97,88,70,62,62,62,63,63,64,66,67,126,-77,-69,-61,-63,-61,-64,-62,-64,-62,-76,116,83,77,73,74,72,75,73,78,74,104,-93,-76,-70,-70,-71,-71,-73,-73,-76,-75,-116,99,91,86,88,87,89,89,90,91,94,-124,-97,-93,-89,-91,-90,-93,-92,-95,-94,-104,120,106,103,102,102,102,103,103,105,103,117,-118,-113,-111,-111,-111,-111,-112,-112,-113,-113,-125,121,120,119,119,119,119,119,120,119,123,-120,-114,-112,-112,-112,-112,-113,-112,-113,-112,-119,124,121,120,121,120,120,121,121,121,121,-117,-100,-97,-95,-96,-96,-98,-97,-99,-98,-104,121,104,101,99,100,99,101,100,103,100,119,-97,-84,-81,-80,-81,-81,-83,-83,-86,-84,-122,91,82,77,79,78,80,80,81,82,88,-111,-75,-69,-65,-68,-68,-71,-72,-74,-80,112,74,67,65,66,68,69,72,71,90}; }
	private static byte[] data2(){ return new byte[] {-93,-65,-58,-59,-58,-62,-62,-67,-62,-100,80,61,53,56,55,60,58,64,59,108,-76,-61,-53,-56,-55,-59,-58,-63,-61,-128,66,56,50,53,53,56,57,59,66,-108,-62,-53,-50,-52,-53,-55,-57,-57,-73,97,58,49,49,50,53,53,58,54,86,-85,-60,-51,-53,-51,-56,-54,-61,-55,-115,52,31,21,25,23,29,27,34,29,100,-75,-60,-51,-54,-52,-56,-55,-59,-61,111,48,34,28,29,29,30,32,31,48,-110,-64,-52,-50,-49,-51,-51,-54,-52,-74,100,69,59,59,58,61,58,63,57,98,-78,-57,-48,-50,-48,-52,-49,-54,-50,-109,80,67,59,61,59,63,61,65,63,-127,-69,-58,-52,-53,-52,-54,-54,-55,-62,119,75,66,63,63,63,64,65,64,84,-98,-73,-63,-64,-61,-65,-61,-67,-61,-105,93,78,73,72,73,71,75,72,114,-89,-77,-72,-73,-73,-73,-77,-74,-105,103,92,87,87,87,88,90,88,109,-106,-96,-91,-92,-92,-92,-94,-92,-106,118,109,106,106,106,106,107,106,114,-119,-111,-108,-108,-108,-108,-109,-109,-114,-128,123,122,122,122,122,122,121,125,-117,-112,-110,-110,-111,-111,-111,-111,-114,-128,121,120,119,120,120,120,119,122,-112,-101,-99,-98,-99,-100,-101,-101,-107,118,102,98,97,97,98,98,99,103,-112,-89,-84,-83,-83,-84,-85,-86,-89,119,86,80,77,78,79,79,82,82,-121,-78,-70,-66,-68,-69,-70,-72,-75,122,77,70,68,69,71,71,74,74,-121,-68,-59,-55,-57,-59,-59,-63,-62,-121,73,61,55,57,59,59,63,61,114,-73,-60,-53,-55,-56,-57,-61,-58,-106,75,59,51,53,53,54,58,56,107,-72,-58,-49,-52,-52,-55,-59,-59,-111,69,57,47,52,48,55,50,76,-97,-60,-54,-50,-53,-52,-58,-58,116,44,30,22,25,22,30,25,71,-89,-62,-51,-52,-50,-54,-53,-65,101,43,32,25,28,26,33,27,82,-84,-61,-50,-50,-47,-52,-50,-66,110,69,61,56,57,56,61,58,119,-72,-59,-50,-51,-48,-53,-49,-80,97,69,60,58,58,59,61,66,-119,-67,-59,-51,-54,-51,-57,-52,-97,87,70,60,61,58,62,59,75,-110,-76,-68,-64,-65,-64,-68,-65,-109,90,78,70,71,69,73,70,89,-106,-81,-75,-74,-75,-75,-77,-78,-123,96,90,85,88,86,90,87,109,-107,-96,-92,-92,-91,-92,-91,-96,-127,112,108,106,107,107,108,107,123,-113,-110,-107,-108,-107,-109,-108,-113,-126,124,123,122,122,122,123,122,-123,-112,-111,-109,-110,-110,-111,-110,-116,126,122,120,120,120,120,120,122,-113,-99,-97,-96,-97,-97,-99,-100,126,106,102,100,100,100,101,104,-111,-88,-83,-81,-82,-83,-83,-94,109,87,81,80,79,82,80,103,-91,-73,-66,-68,-66,-72,-69,-108,86,73,65,69,67,73,70,118,-74,-61,-54,-58,-57,-62,-61,-126,72,61,55,58,58,62,67,-113,-66,-57,-53,-55,-56,-59,-68,108,64,54,52,52,55,55,76,-93,-61,-50,-52,-51,-57,-55,-92,82,59,49,51,48,54,49,103,-75,-59,-49,-54,-51,-59,-54,-121,50,31,20,23,21,27,27,112,-72,-58,-50,-52,-51,-54,-61,101,44,29,25,24,27,26,49,-109,-67,-54,-52,-50,-52,-49,-73,99,67,56,56,55,59,56,92,-85,-63,-51,-53,-48,-53,-48,-101,83,68,58,60,57,61,59,124,-71,-60,-52,-54,-51,-55,-55,-122,79,68,61,62,61,62,70,-116,-76,-66,-62,-62,-63,-62,-78,111,84,74,73,71,75,71,105,-93,-80,-73,-74,-74,-75,-84,118,96,88,88,87,90,88,123,-102,-95,-92,-92,-93,-92,-107,117,109,105,105,105,106,108,-125,-114,-110,-110,-109,-110,-110,-120,125,122,121,121,122,122,127,-115,-111,-110,-110,-110,-111,-112,-125,123,121,121,120,121,120,-121,-101,-97,-95,-96,-97,-97,-106,117,106,101,102,101,103,104,-113,-88,-81,-81,-80,-83,-80,-111,97,86,81,82,81,81,94,-99,-76,-67,-69,-68,-73,-74,124,78,68,66,66,70,68,105,-80,-64,-57,-60,-60,-63,-75,100,65,55,56,55,61,59,124,-72,-59,-55,-56,-59,-57,-93,81,60,50,53,52,56,65,-106,-63,-52,-52,-51,-57,-55,-116,70,56,49,51,52,52,81,-85,-60,-48,-52,-49,-55,-59,101,43,26,24,21,28,22,89,-78,-58,-48,-49,-49,-49,-74,79,41,26,27,24,29,29,119,-72,-57,-52,-49,-52,-49,-115,75,62,55,55,55,58,127,-71,-61,-53,-55,-52,-58,125,71,64,56,59,56,63,-121,-67,-60,-52,-56,-51,-63,120,73,68,59,64,58,75,-111,-73,-68,-61,-65,-59,-80,111,82,78,73,76,72,91,-105,-79,-75,-71,-75,-71,-92,114,95,91,88,91,88,109,-107,-95,-92,-90,-92,-90,-109,117,109,107,106,107,106,122,-115,-110,-108,-109,-109,-109,-118,126,123,121,121,121,120,-126,-115,-114,-112,-113,-113,-114,-125,120,119,118,118,118,119,-117,-101,-100,-98,-99,-99,-101,127,105,102,99,101,99,105,-113,-87,-85,-80,-84,-81,-92,115,87,84}; }
	private static byte[] data3(){ return new byte[] {79,83,79,97,-99,-71,-68,-63,-68,-64,-84,107,76,73,69,75,69,97,-88,-60,-57,-54,-61,-55,-92,88,64,60,57,62,57,102,-78,-59,-54,-53,-58,-53,-98,80,60,53,53,56,52,106,-73,-59,-52,-55,-56,-58,-125,64,56,46,53,46,75,-95,-59,-54,-50,-55,-50,-123,46,29,19,23,18,40,-114,-62,-54,-47,-52,-45,-107,55,33,21,25,20,42,-116,-66,-58,-51,-55,-49,-100,79,62,53,55,52,63,-115,-67,-60,-52,-57,-49,-90,89,66,58,58,57,61,-123,-68,-59,-52,-56,-49,-82,97,70,63,61,62,61,121,-79,-69,-61,-65,-60,-81,109,81,75,72,74,72,117,-88,-79,-73,-76,-73,-91,113,93,88,85,87,86,117,-105,-99,-94,-96,-94,-102,123,107,105,103,104,103,118,-118,-114,-111,-112,-111,-113,-126,122,121,119,120,119,127,-116,-113,-112,-112,-112,-113,-125,122,121,119,121,119,-127,-104,-97,-94,-95,-95,-96,-123,109,106,102,105,101,120,-96,-83,-80,-79,-80,-82,-125,93,87,82,85,81,102,-94,-71,-66,-64,-67,-66,-118,83,75,69,73,69,88,-95,-62,-58,-54,-59,-58,-119,73,64,55,60,56,112,-73,-61,-52,-58,-53,-93,81,60,51,54,51,76,-95,-63,-55,-54,-55,-64,110,60,52,46,51,50,124,-66,-57,-48,-54,-48,-112,50,30,17,22,15,61,-91,-60,-48,-49,-45,-68,86,38,25,21,23,29,124,-69,-58,-49,-53,-50,-115,75,62,52,56,51,108,-76,-63,-52,-57,-50,-88,90,68,56,58,54,76,-98,-65,-55,-53,-53,-61,120,74,65,59,61,59,120,-82,-71,-63,-67,-63,-106,91,77,69,72,67,95,-101,-84,-77,-78,-76,-89,115,93,88,86,86,90,-126,-101,-97,-94,-95,-95,-124,110,106,104,105,104,121,-116,-112,-110,-111,-109,-116,127,124,122,124,123,127,-115,-111,-109,-109,-109,-111,-124,124,122,121,122,122,-115,-99,-96,-94,-96,-95,-116,111,106,102,104,101,121,-95,-84,-79,-81,-79,-92,111,90,84,84,82,93,-102,-73,-66,-65,-66,-80,108,78,70,71,69,92,-89,-64,-55,-58,-55,-91,85,65,55,60,55,104,-77,-61,-52,-57,-53,-115,71,59,50,56,54,117,-71,-60,-51,-56,-55,126,64,53,47,50,56,-115,-63,-53,-49,-49,-64,88,37,21,20,16,46,-101,-61,-47,-48,-43,-74,77,37,21,22,16,61,-93,-64,-50,-53,-47,-95,83,64,52,55,50,109,-76,-62,-52,-55,-53,-119,77,65,55,57,56,124,-72,-61,-53,-55,-59,123,74,63,58,56,67,-116,-79,-68,-65,-63,-80,108,81,71,72,68,93,-102,-84,-76,-78,-74,-97,108,93,86,88,85,109,-108,-99,-93,-95,-93,-115,113,108,105,106,105,125,-114,-110,-108,-108,-109,-121,126,124,123,123,124,-120,-111,-110,-109,-109,-111,-125,124,122,122,121,126,-106,-98,-95,-96,-94,-107,117,107,103,104,101,123,-93,-84,-79,-82,-78,-113,97,88,83,84,88,-109,-75,-67,-67,-65,-92,95,77,68,73,69,127,-71,-60,-56,-57,-75,97,67,56,60,56,106,-75,-60,-52,-56,-59,117,66,55,54,54,81,-89,-63,-52,-55,-53,-125,65,53,48,49,66,-99,-61,-48,-50,-44,-100,55,31,16,21,20,118,-67,-51,-47,-43,-81,68,36,19,23,17,97,-76,-59,-49,-50,-60,110,70,56,55,48,89,-85,-64,-53,-54,-55,126,76,63,59,54,78,-93,-67,-54,-56,-50,-107,81,67,57,58,63,-120,-80,-68,-66,-61,-89,99,80,70,72,72,127,-89,-80,-77,-76,-89,113,95,87,88,85,113,-106,-98,-94,-95,-96,-127,111,106,106,104,115,-118,-113,-109,-110,-108,-119,127,124,124,123,126,-116,-111,-109,-110,-109,-117,126,124,122,122,124,-110,-98,-95,-96,-94,-108,116,108,103,104,103,-120,-88,-82,-79,-80,-88,115,92,84,85,82,-127,-79,-69,-66,-65,-88,97,78,70,72,77,-103,-67,-56,-59,-55,-110,77,62,58,57,85,-85,-63,-52,-57,-58,117,67,55,57,53,111,-73,-59,-52,-53,-74,89,59,48,51,53,-116,-65,-50,-51,-45,-105,52,27,16,15,43,-99,-62,-45,-49,-45,117,44,24,20,14,62,-90,-63,-51,-51,-63,104,69,54,56,49,116,-75,-60,-55,-50,-87,89,70,58,58,62,-110,-69,-56,-56,-50,-114,80,66,61,56,82,-98,-76,-66,-66,-70,116,83,71,72,68,112,-94,-82,-78,-76,-96,107,93,86,88,89,-126,-103,-96,-96,-93,-113,114,107,105,104,110,-122,-114,-111,-111,-111,-123,124,122,121,121,-127,-114,-112,-110,-110,-112,-126,125,122,123,122,-113,-99,-95,-96,-94,-110,115,107,104,103,112,-101,-85,-79,-81,-79,-122,96,88,85,83,113,-83,-72,-63,-67,-74,110,81,70,74,73,-110,-68,-56,-59,-54,-121,75,61,60,56,118,-72,-57,-55,-51,-98,78,61,56,54,92,-79,-61,-52,-52,-76,86,59,48,49,67,-92,-62,-47,-50,-56,89,36,16,18,14,116,-69,-49,-49,-44,121,44,23,20,13,85,-80,-59,-52,-46,-97,80,62,55}; }
	private static byte[] data4(){ return new byte[] {51,83,-87,-63,-54,-51,-77,94,71,59,58,69,-101,-67,-54,-54,-61,112,75,62,62,61,-124,-79,-66,-65,-62,-123,88,76,75,72,117,-90,-79,-75,-74,-108,102,92,88,87,105,-109,-100,-95,-95,-107,115,109,103,105,108,-122,-116,-111,-113,-113,127,124,119,122,120,-121,-116,-111,-114,-111,-127,124,119,122,118,-117,-103,-97,-99,-95,-121,112,103,105,100,126,-93,-81,-80,-76,-103,104,91,87,84,103,-91,-71,-64,-64,-75,111,81,73,72,81,-100,-64,-55,-56,-57,125,73,62,60,60,-125,-67,-56,-53,-51,-109,74,63,55,58,127,-65,-57,-49,-56,126,61,56,45,56,-122,-58,-55,-41,-65,90,27,22,7,37,-114,-56,-52,-38,-71,82,27,24,8,58,-98,-59,-55,-44,-88,87,59,56,46,92,-86,-61,-56,-50,-108,80,63,59,55,114,-76,-60,-54,-53,-114,78,66,58,64,-127,-76,-69,-61,-69,126,83,78,70,81,-118,-83,-80,-72,-89,118,94,92,87,102,-114,-97,-95,-91,-104,121,109,107,105,118,-118,-112,-110,-110,-118,126,123,122,120,-128,-116,-114,-113,-114,-124,121,119,118,119,-119,-102,-101,-98,-101,-128,105,104,99,107,-111,-87,-86,-80,-92,115,87,86,79,96,-103,-72,-71,-62,-93,98,74,73,65,102,-83,-60,-59,-51,-95,86,64,61,56,114,-71,-57,-52,-50,-112,74,62,56,56,121,-66,-57,-48,-56,123,62,55,46,58,-116,-57,-51,-41,-58,99,30,23,7,40,-108,-54,-50,-36,-95,59,26,17,14,99,-72,-57,-45,-60,115,63,58,46,80,-95,-62,-56,-49,-103,81,65,55,61,-128,-70,-61,-48,-71,108,68,63,53,93,-94,-72,-65,-63,-115,87,78,70,80,-120,-84,-81,-73,-94,112,93,90,86,114,-105,-97,-93,-95,-122,112,109,105,112,-123,-112,-110,-108,-116,127,123,123,122,-124,-113,-111,-110,-112,-125,123,122,120,-127,-106,-97,-97,-95,-115,112,106,103,105,-115,-86,-83,-77,-92,114,90,87,81,112,-87,-72,-67,-70,127,78,73,65,85,-99,-62,-60,-52,-95,85,62,57,55,119,-70,-60,-51,-66,113,63,60,48,82,-91,-58,-54,-48,-105,72,55,46,51,-127,-60,-53,-40,-67,84,26,20,5,63,-86,-52,-44,-40,-121,43,26,13,27,126,-63,-56,-42,-75,97,61,56,47,101,-79,-60,-51,-54,-123,73,64,54,73,-106,-64,-57,-46,-96,86,68,58,64,-126,-75,-69,-57,-93,99,80,74,75,127,-85,-80,-72,-96,110,94,89,90,127,-101,-97,-92,-104,121,109,106,105,124,-115,-112,-110,-114,-127,122,121,121,-125,-114,-112,-110,-115,127,121,121,119,-122,-101,-98,-95,-103,122,106,105,102,-125,-89,-83,-77,-87,120,91,88,82,121,-81,-70,-63,-72,121,78,75,67,111,-75,-59,-52,-64,115,67,62,53,95,-80,-58,-52,-59,121,66,60,50,86,-85,-57,-50,-53,127,62,55,41,83,-83,-53,-46,-45,125,35,21,4,44,-100,-56,-47,-43,123,36,22,6,36,-112,-62,-54,-46,-113,71,58,46,63,-111,-66,-59,-49,-104,79,65,52,70,-108,-67,-60,-48,-96,85,67,55,67,-118,-75,-68,-56,-91,99,81,73,79,-121,-83,-79,-71,-100,107,94,90,91,-128,-99,-96,-90,-106,120,110,107,108,-128,-113,-110,-108,-114,-127,124,122,123,-120,-112,-111,-110,-118,125,123,120,127,-107,-98,-96,-96,-124,109,106,101,127,-91,-85,-78,-93,111,90,85,85,-120,-76,-71,-62,-100,90,76,68,84,-98,-61,-58,-54,-119,72,64,53,90,-83,-60,-53,-63,114,64,59,50,111,-69,-57,-46,-76,90,56,48,51,-122,-56,-50,-36,-101,49,23,8,32,-109,-56,-44,-44,113,33,22,6,70,-82,-59,-44,-64,104,62,54,49,119,-71,-61,-47,-85,89,66,55,65,-114,-68,-60,-49,-107,79,67,53,79,-102,-75,-66,-68,126,81,75,67,105,-96,-85,-77,-90,115,92,88,87,122,-104,-99,-94,-111,115,107,103,107,-127,-115,-113,-112,-123,123,121,119,127,-116,-113,-111,-114,-127,122,121,120,-119,-100,-97,-93,-106,118,108,105,108,-109,-84,-81,-77,-111,99,91,83,102,-94,-70,-65,-67,-127,81,77,68,112,-73,-60,-51,-77,96,67,59,67,-109,-61,-57,-53,-119,70,62,50,109,-69,-57,-46,-82,82,57,43,68,-92,-53,-44,-50,101,28,16,9,107,-63,-51,-36,-104,46,23,6,44,-99,-62,-47,-64,101,60,51,51,-127,-69,-60,-51,-118,73,63,51,95,-83,-67,-52,-83,93,68,57,69,-112,-74,-66,-63,-122,85,78,71,119,-89,-81,-73,-100,106,95,87,103,-112,-98,-93,-98,127,111,107,107,-128,-114,-111,-110,-120,125,123,121,127,-115,-112,-110,-115,127,123,121,123,-111,-99,-98,-99,-127,107,104,100,-128,-92,-87,-80,-109,98,88,80,98,-97,-74,-68,-74,116,77,72,71,-118,-66,-61,-53,-107,75,64,52,97,-79,-62,-53,-75,95,62,53,65,-105,-60,-54,-51,-127,61,53,41,100,-66,-51,-36,-88,56,24,8,32,-106,-55,-41,-50,97,31,19,10,99,-69,-56}; }
	private static byte[] data5(){ return new byte[] {-41,-103,74,59,46,81,-87,-64,-49,-79,93,67,55,74,-99,-67,-54,-68,106,72,59,72,-108,-73,-64,-68,123,84,76,77,-120,-86,-78,-80,-128,97,91,89,124,-102,-97,-94,-121,112,108,105,124,-115,-112,-110,-121,124,123,121,-126,-114,-112,-111,-119,124,122,120,-123,-102,-99,-95,-110,115,108,102,120,-94,-84,-77,-98,106,92,84,101,-93,-72,-63,-80,105,81,71,89,-90,-61,-53,-63,111,69,60,67,-106,-63,-55,-60,113,66,57,60,-111,-60,-53,-50,127,61,52,45,127,-58,-48,-40,122,32,18,3,85,-70,-53,-36,-112,41,21,4,76,-79,-59,-43,-89,79,58,43,83,-87,-66,-51,-87,87,63,51,75,-98,-69,-56,-75,99,68,55,73,-107,-75,-65,-72,116,82,73,78,-117,-86,-78,-81,125,98,92,93,-122,-99,-95,-93,-121,113,109,107,127,-114,-111,-109,-121,125,124,122,-124,-113,-111,-110,-119,125,123,121,-117,-100,-97,-96,-123,110,106,106,-112,-86,-81,-84,122,93,87,90,-108,-74,-66,-74,112,80,71,86,-92,-63,-52,-77,93,67,55,88,-83,-63,-50,-91,80,62,49,101,-73,-58,-44,-96,72,54,40,103,-65,-51,-37,-122,36,18,6,103,-64,-48,-40,113,32,15,15,123,-65,-49,-54,110,63,48,62,-105,-65,-53,-64,108,68,54,71,-100,-68,-54,-77,98,69,54,85,-95,-74,-61,-94,96,81,70,107,-94,-83,-75,-111,101,94,87,115,-105,-98,-93,-118,112,107,104,123,-117,-114,-113,-126,121,119,120,-123,-116,-114,-116,127,120,118,122,-110,-102,-98,-107,116,106,100,111,-102,-88,-81,-99,104,90,81,108,-88,-74,-64,-102,89,77,67,118,-71,-60,-51,-114,75,64,59,-126,-65,-57,-50,-120,70,59,55,-122,-62,-51,-51,117,60,48,54,-105,-54,-40,-56,80,26,6,44,-88,-54,-33,-98,47,21,5,95,-70,-54,-43,-124,66,51,54,-114,-68,-53,-71,98,68,50,88,-86,-67,-53,-107,81,64,55,122,-81,-68,-70,117,83,72,87,-106,-87,-77,-102,105,93,86,116,-106,-99,-96,-125,111,106,107,-126,-115,-112,-115,127,123,120,127,-116,-113,-111,-121,124,122,122,-113,-99,-96,-100,123,109,103,117,-97,-85,-78,-106,101,91,84,-125,-78,-69,-68,123,82,72,84,-93,-63,-51,-82,89,67,56,106,-73,-60,-50,-120,70,58,59,-112,-62,-49,-65,94,57,41,78,-78,-53,-36,-108,40,17,5,102,-65,-47,-47,96,28,10,20,-121,-65,-46,-71,89,59,42,87,-83,-64,-51,-111,76,60,54,-126,-73,-59,-66,110,72,56,77,-100,-76,-60,-97,94,81,72,119,-88,-80,-77,-125,100,92,97,-117,-99,-93,-103,121,112,106,119,-117,-111,-110,-119,116,80,61,52,49,49,49,50,52,53,55,56,58,60,61,63,64,66,67,68,70,71,73,74,75,76,78,79,80,81,82,83,84,85,86,88,89,90,91,92,93,94,95,95,96,97,98,99,100,101,102,102,103,104,105,106,106,107,108,109,109,110,111,111,112,113,113,114,115,115,116,117,118,119,119,119,120,120,121,121,122,122,123,123,124,124,124,125,125,126,126,126,127,127,127,-128,-128,-128,-127,-127,-127,-126,-126,-126,-125,-125,-125,-124,-124,-124,-124,-124,-123,-123,-123,-124,-124,-124,-124,-124,-124,-123,-123,-123,-123,-123,-123,-122,-122,-122,-122,-122,-121,-121,-121,-121,-121,-121,-120,-120,-120,-120,-120,-120,-119,-119,-119,-119,-119,-119,-118,-118,-118,-118,-118,-118,-118,-117,-117,-117,-117,-117,-116,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-115,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-116,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-116,-116,-116,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-117,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-118,-119,-119,-119,-119,-120,-120,-120,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-122,-122,-122,-122,-122,-122,-122,-122,-122,-122,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-120,-120,-119,-120,-120,-120,-120,-120,-120,-120,-120,-120,-120,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-121,-122,-122,-122,-122,-122,-122,-122,-122,-122,-122,-122,-122,-122,-123,-123,-123,-123,-123,-123,-123,-123,-123,-123,-123,-123,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-123,-123,-123,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-124,-125,-125}; }
	private static byte[] data6(){ return new byte[] {-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-126,-126,-126,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-126,-126,-126,-126,-126,-125,-125,-124,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-125,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-126,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-127,-126,-126,-126,-126,-127,-127,-127,-127,-127,-127,-127,-128,127,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128,-128}; }
	private static byte[] getData(){
		byte[] data = new byte[6324];
		System.arraycopy(data0(), 0, data, 0, 1024);
		System.arraycopy(data1(), 0, data, 1024, 1024);
		System.arraycopy(data2(), 0, data, 2048, 1024);
		System.arraycopy(data3(), 0, data, 3072, 1024);
		System.arraycopy(data4(), 0, data, 4096, 1024);
		System.arraycopy(data5(), 0, data, 5120, 1024);
		System.arraycopy(data6(), 0, data, 6144, 180);
		return data;
	}
	/** Constructs a new AudioClip with the data from pacman_eatghost.wav. */
	public EatGhost(){
		data = getData();
		format = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, (float)(11025.0), 8, 1, 1, (float)(11025.0), false);
		lineInfo = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);
		try{ AudioSystem.getLine(lineInfo); }
		catch(IllegalArgumentException ex){ lineInfo = null; }
		catch(LineUnavailableException e){}
	}
	/** A separate thread for playing pacman_eatghost.wav. */
	private class PlayThread extends Thread {
		private byte[] data;
		private AudioFormat format;
		private DataLine.Info lineInfo;
		private SourceDataLine line = null;
		private boolean playing = true;
		public PlayThread(byte[] d, AudioFormat f, DataLine.Info i){ data = d; format = f; lineInfo = i; }
		public void run(){
			try{
				line = (SourceDataLine) AudioSystem.getLine(lineInfo);
				line.open(format, AudioSystem.NOT_SPECIFIED);
				line.start();
				int written = 0;
				int available;
				while(written < data.length && playing){
					available = Math.min(line.available(), data.length - written);
					line.write(data, written, available);
					written += available;
				}
				int frames = data.length / format.getFrameSize();
				while(line.getFramePosition() < frames && playing) Thread.sleep(0);
			}
			catch(InterruptedException ex){ playing = false; }
			catch(LineUnavailableException ex){}
			if(line != null){ line.stop(); line.flush(); line.close(); }
			playing = false;
		}
		public void interrupt(){ playing = false; }
		public boolean isPlaying(){ return playing; }
	}
	/** A separate thread for looping play of pacman_eatghost.wav. */
	private class LoopThread extends Thread {
		private EatGhost clip;
		private boolean looping = true;
		public LoopThread(EatGhost c){ clip = c; }
		public void run(){
			while(looping){
				clip.play();
				while(clip.isPlaying() && looping){
					try{ Thread.sleep(0); }
					catch(InterruptedException ex){ looping = false; break; }
				}
			}
			if(!clip.isLooping()) clip.stop();
		}
		public void interrupt(){ looping = false; }
		public boolean isLooping(){ return looping; }
	}
	/** Plays pacman_eatghost.wav from the beginning, even if it is already playing or looping. */
	public void play(){ if(lineInfo == null) return; doPlay(); }
	private synchronized void doPlay(){
		doStopPlay();
		playThread = new PlayThread(data, format, lineInfo);
		playThread.start();
	}
	/** Plays pacman_eatghost.wav continuously until stopped. */
	public void loop(){ if(lineInfo == null) return; doLoop(); }
	private synchronized void doLoop(){
		doStopLoop();
		loopThread = new LoopThread(this);
		loopThread.start();
	}
	/** Stops play and looping of pacman_eatghost.wav. */
	public void stop(){ if(lineInfo == null) return; doStop(); }
	private synchronized void doStop(){
		doStopPlay();
		doStopLoop();
	}
	private void doStopPlay(){
		if(playThread == null) return;
		if(playThread.isPlaying()) playThread.interrupt();
		playThread = null;
	}
	private void doStopLoop(){
		if(loopThread == null) return;
		if(loopThread.isLooping()) loopThread.interrupt();
		loopThread = null;
	}
	/** Tests if pacman_eatghost.wav is currently playing or looping.
	 * @return <tt>true</tt> if playing or looping, <tt>false</tt> otherwise */
	public boolean isPlaying(){ if(lineInfo == null) return false; return doIsPlaying(); }
	private synchronized boolean doIsPlaying(){
		if(loopThread == null && playThread == null) return false;
		else if(loopThread == null) return playThread.isPlaying();
		else if(playThread == null) return loopThread.isLooping();
		else return loopThread.isLooping() && playThread.isPlaying();
	}
	/** Tests if pacman_eatghost.wav is currently looping.
	 * @return <tt>true</tt> if looping, <tt>false</tt> otherwise */
	public boolean isLooping(){ if(lineInfo == null) return false; return doIsLooping(); }
	private synchronized boolean doIsLooping(){
		if(loopThread == null) return false;
		else return loopThread.isLooping();
	}
}