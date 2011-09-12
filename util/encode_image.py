import base64
import sys


if __name__ == "__main__":
	if len(sys.argv) < 2:
		print "usage: encode_image.py <filename>"
		sys.exit(-1)
	filename = sys.argv[1]
	
	try:
		file = open(filename, "rb")
		file_content = file.read()
		file.close()
		

		
		
	except:
		print "can not open file: %s" % filename
		sys.exit(-2)
	
	b64_str  = base64.b64encode(file_content)
	
	out = ""
	if filename.endswith(".png"):
		out = "data:image/png;base64," + b64_str
	else:
		out = b64_str
	
	print out
	
	
	