class Candidate {

    int candidateId;
    String candidateName;
    int voteCount;

    Candidate(int candidateId, String candidateName, int voteCount) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.voteCount = voteCount;
    }
}

public class SecureVoteHeapSort {

    // Heapify Function
    static void heapify(Candidate arr[], int n, int i) {

        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left].voteCount > arr[largest].voteCount) {
            largest = left;
        }

        if (right < n && arr[right].voteCount > arr[largest].voteCount) {
            largest = right;
        }

        if (largest != i) {

            Candidate temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }
    }

    // Heap Sort
    static void heapSort(Candidate arr[]) {

        int n = arr.length;

        // Build Max Heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract Elements
        for (int i = n - 1; i > 0; i--) {

            Candidate temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    // Display Candidates
    static void display(Candidate arr[]) {

        for (Candidate c : arr) {
            System.out.println(
                c.candidateId + "  "
                + c.candidateName + "  "
                + c.voteCount + " votes"
            );
        }
    }

    public static void main(String[] args) {

        Candidate candidates[] = {

            new Candidate(101, "Candidate A", 450),
            new Candidate(102, "Candidate B", 620),
            new Candidate(103, "Candidate C", 300),
            new Candidate(104, "Candidate D", 780),
            new Candidate(105, "Candidate E", 510)

        };

        System.out.println("SecureVote - Election Analytics System");
        System.out.println("--------------------------------------");

        System.out.println("\nBefore Heap Sort:");
        display(candidates);

        // Fraud Detection Example
        System.out.println("\nFraud Detection Check:");

        boolean fraud = false;

        for (Candidate c : candidates) {

            if (c.voteCount > 10000) {
                fraud = true;
            }
        }

        if (fraud) {
            System.out.println("Suspicious Vote Count Detected!");
        } else {
            System.out.println("No Fraudulent Activity Detected.");
        }

        // Heap Sort
        heapSort(candidates);

        System.out.println("\nElection Results After Heap Sort:");
        display(candidates);

        System.out.println("\nWinner:");
        Candidate winner = candidates[candidates.length - 1];

        System.out.println(
            winner.candidateName +
            " with " +
            winner.voteCount +
            " votes"
        );
    }
} 
