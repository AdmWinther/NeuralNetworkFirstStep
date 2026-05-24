# AI Image Classifier — Neural Network from First Principles

A fully-connected feedforward neural network that trains on the **MNIST** dataset and classifies handwritten digits (0–9). Built from scratch in Java with a strong focus on the **interface-first design approach** and classical **Gang-of-Four design patterns**.

> Developed as an assignment for the course *Higher Level Topics in Programming*.

---

## Table of Contents

- [What it does](#what-it-does)
- [Architecture](#architecture)
- [Design Patterns](#design-patterns)
- [Main Menu Structure](#main-menu-structure)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Dependencies](#dependencies)

---

## What it does

- Trains a neural network on the MNIST dataset (60,000 training images, 10,000 test images — 28×28 pixel greyscale handwritten digits)
- Classifies a digit from a **PNG image** or a **CSV file**
- Evaluates model accuracy on the full test set
- Saves and loads trained models as **JSON files** (no retraining needed)
- Provides an interactive command-line menu for all operations

---

## Architecture

The project follows the **Model-View-Controller (MVC)** architectural pattern, with a strict **interface-first** discipline: every major component is defined as a Java interface before any concrete class is written.

```
org.example
├── Model        — Neural network, training, data, serialisation
├── View         — Menu system (Composite pattern) and I/O abstraction
└── Controller   — One controller class per user action
```

All layers communicate exclusively through interfaces, which means:
- The `TerminalUserInterface` can be replaced by a GUI or web implementation without touching the Model or Controller
- The Gson serialisation library is fully isolated behind `IAiJsonAdapter` and can be swapped without affecting any other class

### Key interfaces and their implementations

| Interface | Implementation | Layer |
|---|---|---|
| `IAI` | `AI` | Model — neural network |
| `INode` | `Node` | Model — neuron (value, bias, delta) |
| `IConnection` | `Connection` | Model — weighted edge |
| `IAppState` | `AppState` | Model — shared application state |
| `IActivationFunction` | `ReLU`, `Sigmoid`, `Tanh`, `SoftSign`, `Step` | Model — activation |
| `IClassifierAIFactory` | `AiFactory` | Model — factory + builder |
| `IJsonableConnection` | `JsonableConnection` | Model/Tools — serialisation DTO |
| `IJsonableNode` | `JsonableNode` | Model/Tools — serialisation DTO |
| `IAiJsonAdapter` | `AiJsonAdapter` | Model/Tools — Gson adapter |
| `IUserInterface` | `TerminalUserInterface` | View — terminal I/O |
| `IMenuController` | `NewAIModelController`, `TrainModelController`, … | Controller |

---

## Design Patterns

### MVC (Model-View-Controller)
The three packages `Model`, `View`, and `Controller` map directly to MVC layers. Controllers receive `AppState` from the View, operate on the Model, and return control — with no cross-layer imports other than through interfaces.

### Factory + Builder — `AiFactory`
`AiFactory` implements `IClassifierAIFactory` and constructs a fully wired `AI` object from a layer topology (e.g. `[784, 128, 10]`). It creates every `Node` and `Connection`, assigns random initial weights, and returns only an `IAI` reference. A fluent Builder API (`setInitialBias()`, `setInitialWeight()`) allows clean configuration before the final `build()` call.

### Composite — Menu system
The entire menu hierarchy is built with the Composite pattern:

| Class | Role |
|---|---|
| `IMenuComponent` | Abstract base — declares `actionOnSelection()` |
| `SubMenu` | Composite node — holds a list of child `IMenuComponent`s |
| `MenuOption` | Leaf — holds an `IMenuController` and calls `execute(appState)` |
| `MainMenu` | Root `SubMenu` — `build()` wires the full menu tree |

Adding a new menu option only requires writing a new `Controller` and calling `.add()` on the appropriate `SubMenu` — no existing class is modified.

### Adapter — JSON serialisation
Because the AI object graph uses interface fields (`INode`, `IConnection`, `IActivationFunction`), Gson cannot determine the concrete class to deserialise without help. Three adapter classes solve this:

- **`AiJsonAdapter`** — wraps Gson's `GsonBuilder` and registers `RuntimeTypeAdapterFactory` entries for all polymorphic interface types. The rest of the code calls `getGson()` and never imports Gson directly.
- **`JsonableConnection`** — DTO that flattens a `Connection` into three plain fields (`upstreamNodeLayer`, `upstreamNodeIndex`, `connectionWeight`) for serialisation.
- **`JsonableNode`** — DTO that flattens a `Node` (layer, index, bias, activation function, connections) for serialisation.

---

## Main Menu Structure

```
┌ Model
│ ├ Make a new model          — configure and create a new neural network
│ ├ Set training data         — load MNIST training data
│ ├ Train the model           — run backpropagation on training data
│ ├ Test the model            — evaluate accuracy on test data
│ ├ Load a model              — deserialise a previously saved model from JSON
│ ├ Save the model            — serialise the current model to JSON
│ ├ Current Model Properties  — display network topology and training status
│ ├ Weights & Biases Visualizer
│ └ Exit                      — return to main menu
├ Classify
│ ├ From PNG Image            — load a PNG file and predict the digit (0–9)
│ ├ From CSV                  — load a CSV file and predict the digit (0–9)
│ └ Exit                      — return to main menu
└ Exit                        — quit the application
```

The menu tree is configured in `MainMenu.java`. New options can be added there by creating a controller and calling `.add()`.

---

## Project Structure

```
src/main/java/org/example/
├── App.java                          — entry point
├── Model/
│   ├── IAI.java / AI.java            — neural network (train, classify, forward pass)
│   ├── INode.java / Node.java        — neuron (value, bias, delta, connections)
│   ├── IConnection.java / Connection.java
│   ├── IAppState.java / AppState.java
│   ├── NodeCoordinate.java
│   ├── IClassifierAIFactory.java / AiFactory.java
│   ├── IJsonableConnection.java / IJsonableNode.java / IAiJsonAdapter.java
│   ├── ActivationFunction/
│   │   ├── IActivationFunction.java
│   │   └── ReLU.java, Sigmoid.java, Tanh.java, SoftSign.java, Step.java
│   ├── Data/
│   │   └── IData.java + MNIST loaders
│   └── Tools/
│       ├── AiJsonAdapter.java
│       ├── JsonableConnection.java
│       └── JsonableNode.java
├── View/
│   ├── IO/
│   │   ├── IUserInterface.java
│   │   └── TerminalUserInterface.java
│   └── Menu/
│       ├── IMenuComponent.java       — abstract base (Composite pattern)
│       ├── SubMenu.java              — composite node
│       ├── MenuOption.java           — leaf node
│       └── MainMenu.java             — root, builds the full menu tree
└── Controller/
    ├── IMenuController.java
    ├── NewAIModelController.java
    ├── TrainModelController.java
    ├── LoadModelController.java
    ├── SaveModelController.java
    ├── TestModelController.java
    ├── SetTrainingDataController.java
    ├── PNGImageClassifierController.java
    ├── ClassifyCsvFileController.java
    ├── GetModelPropController.java
    ├── WeightBiosVisualizeController.java
    ├── ExitApplicationController.java
    └── ExitMenuController.java
```

---

## Getting Started

### Prerequisites

- Java 25+
- Apache Maven

### Build and run

```bash
git clone https://github.com/AdmWinther/NeuralNetworkFirstStep.git
cd NeuralNetworkFirstStep
mvn compile
mvn exec:java -Dexec.mainClass="org.example.App"
```

### Create a new model

Make your model by using 1-1 from menu. The code will ask you the number of layers, for start, I suggest you use 3 layers to avoid overloading your computer.
Then set the training data by 1-2 from the menu. If you place your MNIST training data set at the default address, it wluld be easier for you.
Note that so far you only introduced the training data and you have not trained the model yet. Train the model by using 1-3 from the main menu.
Wain until training is finished. By default the model is trained by iterating 10 rounds on the training data, if you want finer model with less resedues, you can run the training another time.
After model is trained, it can be used for classifying an image. use any of the options under item number 2 of the main menu for classifying.
You can also use 1-4 to see the percentage that the model can guess the right data.
It is possible to save the trained data to avoid training everytime you open the program, it is done by 1-6. after you saved a model, you can load it by 1-5 in the main menu.

### Classify a custom image

Place a 28×28 greyscale PNG in the project root, then use the menu:
`Classify → From PNG Image`


---

## Dependencies

| Dependency | Version | Purpose |
|---|---|---|
| `com.google.code.gson:gson` | 2.14.0 | JSON serialisation, used exclusively via `AiJsonAdapter` |
| `org.danilopianini:gson-extras` | 1.2.0 | `RuntimeTypeAdapterFactory` for polymorphic interface serialisation |
| `junit:junit` | 3.8.1 | Unit testing |