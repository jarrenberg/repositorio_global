class Restaurante {
  late String name;
  late String cuisine;
  late List<double> ratings;

  Restaurante(String name, String cuisine, List<double> ratings) {
    this.name = name;
    this.cuisine = cuisine;
    this.ratings = ratings;
  }

  int getCantidadRatiings() {
    return this.ratings.length;
  }

  void anyadirRating(double rating) {
    this.ratings.add(rating);
  }

  void anyadirMultiplesRatings(List<double> ratingsExtra) {
    this.ratings.addAll(ratingsExtra);
  }

  double getRatingMedio() {
    double resultado = 0;
    this.ratings.forEach((n) {
      resultado = resultado + n;
    });
    resultado=resultado/ratings.length;
    return resultado;
  }
}

void main(List<String> args) {
  var baratie = ("Restaurante Baratie","Sanji",[1,7.8,5.4]);
}