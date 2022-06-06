#include <cstdlib>
#include <ctime>
#include <vector>
#include <iostream>

using namespace std;

template <typename Key> 
class Selection {
public:
    static void sort(vector<Key> &arr) {
        int N = arr.size();
        for (int i = 0; i < N; ++i) {
            int min = i;
            for (int j = i + 1; j < N; ++j) {
                // arr[j] is less than arr[min]
                if (arr[j] < arr[min])
                    min = j;
            }
            exch(arr, i, min);  
        }
    }

private:
    static void exch(vector<Key> &arr, int i, int j) {
        Key swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
};

class Comp {
public:
    Comp(const int key) : val(key) {}
    int val;
    bool operator<(const Comp &rhs) const {
        return this->val < rhs.val;
    }
};

int main(int argc, char** argv) {
    srand((unsigned)time(NULL));
    vector<Comp> arr;

    for (int i = 0; i < 10; ++i) {
        // a + rand() % b can generate a random number within the range [a, b]
        arr.push_back(Comp(rand() % 10));
    }

    
    Selection<decltype(arr)::value_type>::sort(arr);

    for (Comp &elem : arr)
        cout << elem.val << " " << endl;
    return 0;
}
