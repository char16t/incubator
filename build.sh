# Prepare environment
mkdir -p out

# Build all C++ solutions
for i in algoprog.ru/*.cpp; do
    [ -f "$i" ] || break
    filename=$(basename -- "$i")
    filename="${filename%.*}"
    g++ "$i" -o out/"${filename}"
done

# Build all C solutions
for i in algoprog.ru/*.c; do
    [ -f "$i" ] || break
    filename=$(basename -- "$i")
    filename="${filename%.*}"
    gcc "$i" -o out/"${filename}"
done
