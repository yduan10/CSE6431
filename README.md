
# Restaurant Simulation

This project simulates a restaurant where diners arrive, place orders, and dine. Each diner's order is processed by a cook. There are constraints on the number of tables in the restaurant, the number of cooks, and the machines available to prepare the food.

## Files

- `Restaurant6431.java`: This is the main class that handles the operations of the restaurant.
- `Diner.java`: Represents a diner, handling their arrival, order processing, dining, and departure.
- `Utils.java`: Contains utility functions for time logging, machine usage, and resource availability checks.
- `Makefile`: Provides commands to compile, run, and clean up the compiled files.

## How to Run

1. Compile the project:
   ```
   make
   ```

2. Test with an input file:
   ```
   make test Input.txt
   ```

   Replace the `Input.txt` name with your desired input files.

3. Clean up the compiled files:
   ```
   make clean
   ```

