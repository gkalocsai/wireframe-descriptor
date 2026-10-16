#!/bin/bash

# Define the output file path
OUTPUT_FILE="../merged.txt"

# Clear the output file if it already exists
> "$OUTPUT_FILE"

echo "Bundling .java files..."

# Find all .java files recursively starting from the current directory
# Using -print0 and read -d '' to handle filenames with spaces correctly
find . -type f -name "*.java" -print0 | while IFS= read -r -d '' file; do
    echo "Processing $file..."
    
    # Add a header for each file so you know where the code came from
    echo "================================================================================" >> "$OUTPUT_FILE"
    echo " FILE: $file" >> "$OUTPUT_FILE"
    echo "================================================================================" >> "$OUTPUT_FILE"
    
    # Append the content of the java file to the merged file
    cat "$file" >> "$OUTPUT_FILE"
    
    # Add some newlines for readability between files
    echo -e "\n\n" >> "$OUTPUT_FILE"
done

echo "Done! All .java files have been bundled into $OUTPUT_FILE"
