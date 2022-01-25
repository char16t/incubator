NOW=`date`

build:
	mkdir -p target
	echo $NOW
	@echo "$(NOW)" > target/CURRENT_VERSION
	pdflatex -output-directory="target" -jobname=draft-`date +%Y-%m-%d-%H-%M-%S` main.tex

clean:
	rm -rf target/

env:
	apt install -y texlive-latex-base
	apt install -y texlive-lang-all
	apt install -y texlive-latex-extra

