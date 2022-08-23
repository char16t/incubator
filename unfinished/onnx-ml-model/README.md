Create virtual environment:

```bash
python3 -m venv venv
```

Activate virtual environment:

```bash
source venv/bin/activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Open notebook:

```bash
jupyter notebook notebook.ipynb
```

Export model as ONNX:

```
python3 onnx.py
```

Deactivate virtual environment:

```bash
deactivate
```
