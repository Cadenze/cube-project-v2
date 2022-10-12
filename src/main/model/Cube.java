package model;

/**
 * Emulation of a Rubik's cube in terms of its "cubelets".
 */
public class Cube {
    private int[] edges;   // represents the 12 edge   positions
    private int[] corners; // represents the  8 corner positions
    private int[] parity;  // represents the parities at each position
    private int[] spin;    // represents the spins    at each position

    /**
     * Initializes a solved cube.
     */
    public Cube() {
        edges = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        corners = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        parity = new int[12];
        spin = new int[0];
    }

    // TODO: scrambled initialization

    // TODO: translate moves to action on cube

    /**
     * Turns a face of the cube a certain number of times.
     * @param face Indicates which face we are turning
     * @param turn Number of quarter-turns we are making
     */
    private void turn(String face, int turn) {
        switch (face) {
            case "U":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(0, 1, 2, 3);
                    cycleEdge(0, 1, 2, 3);
                }
                break;

            case "D":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(4, 7, 6, 5);
                    cycleEdge(8, 11, 10, 9);
                }
                break;

            case "F":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(0, 3, 7, 4);
                    cycleEdge(0, 7, 8, 4);

                    editSpin(0, +1);
                    editSpin(3, -1);
                    editSpin(7, +1);
                    editSpin(4, -1);

                    editParity(0);
                    editParity(7);
                    editParity(8);
                    editParity(4);
                }
                break;

            case "B":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(2, 1, 5, 6);
                    cycleEdge(2, 5, 10, 6);

                    editSpin(2, +1);
                    editSpin(1, -1);
                    editSpin(5, +1);
                    editSpin(6, -1);

                    editParity(2);
                    editParity(5);
                    editParity(10);
                    editParity(6);
                }
                break;

            case "L":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(3, 2, 6, 7);
                    cycleEdge(3, 6, 11, 7);

                    editSpin(3, +1);
                    editSpin(2, -1);
                    editSpin(6, +1);
                    editSpin(7, -1);
                }
                break;

            case "R":
                for(int i = 0; i < turn; i++) {
                    cycleCorner(1, 0, 4, 5);
                    cycleEdge(1, 4, 9, 5);

                    editSpin(1, +1);
                    editSpin(0, -1);
                    editSpin(4, +1);
                    editSpin(5, -1);
                }
                break;
        }
    }

    /**
     * Cycles 4 corners and transfers their spins to the position 90 degrees clockwise.
     * @param twelve o'clock position
     * @param nine o'clock position
     * @param six o'clock position
     * @param three o'clock position
     */
    private void cycleCorner(int twelve, int nine, int six, int three) {
        int temp = corners[twelve];
        corners[twelve] = corners[nine];
        corners[nine] = corners[six];
        corners[six] = corners[three];
        corners[three] = temp;

        int s = spin[twelve];
        spin[twelve] = spin[nine];
        spin[nine] = spin[six];
        spin[six] = spin[three];
        spin[three] = s;
    }

    /**
     * Cycles 4 edges and transfers their parities 90 degrees clockwise.
     * @param twelve o'clock position
     * @param nine o'clock position
     * @param six o'clock position
     * @param three o'clock position
     */
    private void cycleEdge(int twelve, int nine, int six, int three) {
        int e = edges[twelve];
        edges[twelve] = edges[nine];
        edges[nine] = edges[six];
        edges[six] = edges[three];
        edges[three] = e;

        int p = parity[twelve];
        parity[twelve] = parity[nine];
        parity[nine] = parity[six];
        parity[six] = parity[three];
        parity[three] = p;
    }

    /**
     * Increments or decrements the spin of a distinct corner.
     * @param corner Corner number
     * @param delta The change in spin
     */
    private void editSpin(int corner, int delta) {
        spin[corner] += delta;
        while(spin[corner] > 2) {
            spin[corner] -= 3;
        }
        while(spin[corner] < 0) {
            spin[corner] += 3;
        }
    }

    /**
     * Changes the parity of a specific edge.
     * @param edge Edge number
     */
    private void editParity(int edge) {
        parity[edge] = parity[edge] == 0 ? 1 : 0;
    }
}
