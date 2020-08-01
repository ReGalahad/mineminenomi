import sys
import os
import re
import fileinput

# How to : python .\build.py <type>
# If a type is not provided it will simply use DEV as a default; Types are always uppercased so no need to worry about case sensitivity

# Variables
api_file_path = "../src/main/java/xyz/pixelatedw/wypi/APIConfig.java"
gradle_file_path = "../gradle.properties"
api_pattern = "(?<=BuildMode\.).*(?=\;)"
gradle_pattern = "(?<=version_type=).*"

# Defining build_type's default value and assigning the parameter if we got it
build_type = "DEV"
if len(sys.argv) > 1:
	build_type = sys.argv[1].upper()

# Opening the file and changing it based on our api_pattern
with fileinput.FileInput(api_file_path, inplace=True, backup='.bak') as file:
	for line in file:
		result = re.findall(api_pattern, line)
		if len(result) > 0:
			print(line.replace(result[0], build_type), end='')
		else:
			print(line, end='')

# Doing the samae as above but with the gradle file
with fileinput.FileInput(gradle_file_path, inplace=True, backup='.bak') as file:
	for line in file:
		result = re.findall(gradle_pattern, line)
		if len(result) > 0:
			print(line.replace(result[0], build_type), end='')
		else:
			print(line, end='')

# Runs the gradlew build command in the parent folder (where the gradle / git files are)
os.chdir("../")
os.system("./gradlew build")