# Lost Cities Java Board Game

A two-player desktop implementation of a Lost Cities-style archaeological board game, built in Java with Swing and organised into Model, View, and Controller layers.

## Purpose

The project was created as an individual university exercise to translate a complete board-game specification into an interactive application. It demonstrates object-oriented modelling, stateful game logic, desktop UI development, and event-driven interaction.

## Features

- Two-player turn management
- Card drawing, playing, and discarding
- Four archaeological paths: Knossos, Malia, Phaistos, and Zakros
- Archaeologist and Theseus pawn types
- Standard and special cards, including Minotaur and Ariadne cards
- Excavation findings, rare artefacts, fresco collections, and scoring
- Swing-based graphical interface with cards, board assets, dialogs, and sound
- End-of-game detection and winner calculation

## Architecture

The source is separated into three areas:

- **Model** - board, deck, cards, players, pawns, paths, findings, and scoring state
- **View** - Swing components, layout, dialogs, board rendering, and visual updates
- **Controller** - turn flow, user-event handling, rule enforcement, and coordination between the model and view

## Technologies

- Java
- Java Swing and AWT
- Object-oriented design
- MVC-style separation
- Event listeners
- File-based image, audio, and CSV assets

## Project structure

```text
src/
  Controller/
  Model/
  View/
project_assets/
  csvFiles/
  images/
  music/
```

## Running the project

1. Install a Java Development Kit.
2. Open the repository as a Java project in your IDE.
3. Keep `project_assets` at the repository root.
4. Run `src/Main.java`.

> Note: this is an archived university submission. Some asset paths in the original implementation are machine-specific and may need to be changed to repository-relative paths before running on another computer.

## What this project demonstrates

This project goes beyond a collection of isolated classes: it coordinates a sizeable domain model, graphical interface, and controller workflow while maintaining distinct responsibilities between application layers.
