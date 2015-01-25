#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;
varying float v_time;

uniform sampler2D u_texture;
void main() {
float realBlur = 10.0 / 100.0;

   vec4 sum = vec4(0.0);
   
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y - 4.0*realBlur)) * 0.05;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y - 3.0*realBlur)) * 0.09;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y - 2.0*realBlur)) * 0.12;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y - realBlur)) * 0.15;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y)) * 0.16;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y + realBlur)) * 0.15;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y + 2.0*realBlur)) * 0.12;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y + 3.0*realBlur)) * 0.09;
	   sum += texture2D(u_texture, vec2(v_texCoords.x, v_texCoords.y + 4.0*realBlur)) * 0.05;
}
