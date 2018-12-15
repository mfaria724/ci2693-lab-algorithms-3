public class DisjointSets{
    int[] parts;
    int n;
    public DisjointSets(int n){
        this.n = n;
        this.parts = new int[n];
        for(int i=0; i<n;i++){
            this.parts[i] = i;
        }
    }

    public int find(int x){
        return this.parts[x];
    }

    public void join(int x, int y){
        for(int i=0; i<n; i++){
            if(this.parts[i]==y){
                this.parts[i]=x;
            }
        }
    }
}