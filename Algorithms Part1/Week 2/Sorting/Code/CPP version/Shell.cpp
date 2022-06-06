#include <cstdlib>
#include <ctime>
#include <vector>
#include <iostream>

using namespace std;

template <typename Key> 
class Shell {
public:
    static void sort(vector<Key> &arr) {
        int N = arr.size();
        int K = 1;
        while (K < N / 3) {
            K = K * 3 + 1;
        }

        while (K >= 1) {
            for (int i = K; i < N; ++i) {
                for (int j = i; j >= K && arr[j] < arr[j - K]; j -= K) {
                    exch(arr, j, j - K);
                }
            }
            K /= 3;
        }
    }
private:
    static void exch(vector<Key> &arr, int i, int j) {
        Key swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
};

template <typename Key>
class Comp {
public:
    Comp(Key key) : val(key) {}
    Key val;
    bool operator<(const Comp &rhs) const {
        return this->val < rhs.val;
    }
};

int main(int argc, char** argv) {
    srand((unsigned)time(NULL));
    vector<Comp<int>> arr;

    for (int i = 0; i < 10; ++i) 
        arr.push_back(Comp<int>(rand() % 10));

    
    Shell<decltype(arr)::value_type>::sort(arr);

    for (Comp<int> &elem : arr)
        cout << elem.val << " " << endl;
}