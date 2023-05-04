<template>
    <div class="container-fluid search-bar bg-secondary" style="padding: 35px;">
            <div class="container">
                <div class="row g-2">
                    <div class="col-md-10">
                        <form class="row g-2">
                            <div class="col-md-4">
                                <input type="text" class="form-control border-0 py-3" placeholder="Que cherchez vous?">
                            </div>
                            <div class="col-md-4">
                                <select class="form-select border-0 py-3">
                                    <option selected>jouets</option>
                                    <option value="1">livres</option>
                                    <option value="2">materiel educatif</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-select border-0 py-3">
                                    <option selected>Paris</option>
                                    <option value="1">Toulouse</option>
                                    <option value="2">Lyon</option>
                                    <option value="3">Marseille</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-2">
                        <button class="btn search-button border-0 w-100 py-3 bg-primary text-light">Chercher</button>
                    </div>
                </div>
            </div>
        </div>
</template>