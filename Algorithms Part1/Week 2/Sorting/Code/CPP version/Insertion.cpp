#include <cstdlib>
#include <time.h>
#include <vector>
#include <iostream>

using namespace std;

#define ELEM_NUM 15
#define ELEM_MAX 10

template <typename Key>
class Insertion {
public:
    static void sort(vector<Key> &arr) {
        int N = arr.size();
        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; --j) {
                exch(arr, j, j - 1);
            }
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

    for (int i = 0; i < ELEM_NUM; ++i) {
        arr.push_back(Comp(rand() % 10));
    }

    Insertion<decltype(arr)::value_type>::sort(arr);

    for (Comp elem : arr)
        cout << elem.val << " " << endl;

    return 0;
}